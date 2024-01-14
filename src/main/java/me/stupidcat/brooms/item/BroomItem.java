package me.stupidcat.brooms.item;

import me.stupidcat.brooms.Brooms;
import me.stupidcat.brooms.BroomsBlockTags;
import me.stupidcat.brooms.BroomsEntities;
import me.stupidcat.brooms.BroomsParticles;
import me.stupidcat.brooms.entity.SparkleCloudEntity;
import me.stupidcat.brooms.recipes.BroomRecipe;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.UseAction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class BroomItem extends Item {
    private static final double MAX_SWEEP_DISTANCE;

    public BroomItem(Settings settings) {
        super(settings);
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BRUSH;
    }
    public int getMaxUseTime(ItemStack stack) {
        return 1200;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity playerEntity = context.getPlayer();

        if (playerEntity != null) {
            playerEntity.setCurrentHand(context.getHand());
        }

        return ActionResult.CONSUME;
    }

    private void tryRemoveBlock(LivingEntity entity, World world, BlockPos blockPos, Direction side) {
        var blockState = world.getBlockState(blockPos);
        if (blockState.isIn(BroomsBlockTags.SWEEPABLE)) {
            removeBlock(world, blockPos, blockState, entity);
        } else if (side == Direction.UP) {
            blockPos = blockPos.up();
            blockState = world.getBlockState(blockPos);

            if (blockState.isIn(BroomsBlockTags.SWEEPABLE)) {
                removeBlock(world, blockPos, blockState, entity);
            }
        }
    }

    private void removeBlock(World world, BlockPos blockPos, BlockState blockState, LivingEntity entity) {
        if (!world.isClient()) {
            boolean shouldDestroy = blockState.isIn(BroomsBlockTags.SWEEPABLE_DESTROY);
            if (!entity.isSneaking() || shouldDestroy) {
                world.breakBlock(blockPos, !shouldDestroy, entity);
            }
        }
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (user instanceof PlayerEntity playerEntity) {
            HitResult hitResult = this.getHitResult(user);
            if (hitResult instanceof BlockHitResult blockHitResult) {
                if (hitResult.getType() == HitResult.Type.BLOCK) {
                    int i = this.getMaxUseTime(stack) - remainingUseTicks + 1;
                    boolean bl = i % 10 == 5;
                    if (bl) {
                        BlockPos blockPos = blockHitResult.getBlockPos();
                        BlockState blockState = world.getBlockState(blockPos);
                        Arm arm = user.getActiveHand() == Hand.MAIN_HAND ? playerEntity.getMainArm() : playerEntity.getMainArm().getOpposite();
                        this.addDustParticles(world, blockHitResult, blockState, user.getRotationVec(0.0F), arm, playerEntity.isSneaking());
                        var side =  blockHitResult.getSide();
                        tryRemoveBlock(playerEntity, world, blockPos, side);
                        if (!playerEntity.isSneaking()) {
                            for (int _x = -1; _x < 2; _x++) {
                                for (int _z = -1; _z < 2; _z++) {
                                    var pos = blockPos.add(new Vec3i(_x, 0, _z));
                                    tryRemoveBlock(playerEntity, world, pos, side);
                                }
                            }
                        }

                        world.playSound(playerEntity, blockPos, SoundEvents.ITEM_BRUSH_BRUSHING_GENERIC, SoundCategory.BLOCKS);
                    }

                    return;
                }
            }
            user.stopUsingItem();
        } else {
            user.stopUsingItem();
        }
    }

    private HitResult getHitResult(LivingEntity user) {
        return ProjectileUtil.getCollision(user, (entity) -> {
            return !entity.isSpectator() && entity.canHit();
        }, MAX_SWEEP_DISTANCE);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        super.onStoppedUsing(stack, world, user, remainingUseTicks);
    }

    public void addDustParticles(World world, BlockHitResult hitResult, BlockState state, Vec3d userRotation, Arm arm, boolean isSneaking) {
        double d = 3.0D;
        int i = arm == Arm.RIGHT ? 1 : -1;
        int j = world.getRandom().nextBetweenExclusive(7, 12);

        BlockStateParticleEffect blockStateParticleEffect = new BlockStateParticleEffect(ParticleTypes.BLOCK, state);
        Direction direction = hitResult.getSide();
        BroomItem.DustParticlesOffset dustParticlesOffset = BroomItem.DustParticlesOffset.fromSide(userRotation, direction);
        Vec3d vec3d = hitResult.getPos();

        // var dustParticleEffect = new BlockStateParticleEffect(ParticleTypes.DUST, )
        if (world instanceof ServerWorld serverWorld) {
            double delta = 0.7;
            int count = 20;
            if (isSneaking) {
                delta = 0.2;
                count = 3;
            }

            serverWorld.spawnParticles(BroomsParticles.BROOM_DUST, vec3d.x, vec3d.y + 0.3, vec3d.z, count, delta, 0, delta, 0.1);

            var sparkles = new SparkleCloudEntity(world, vec3d.x, vec3d.y + 0.1, vec3d.z);
            sparkles.setRadius(isSneaking ? 0.5f : 1.5f);
//            sparkles.setParticleType(ParticleTypes.ELECTRIC_SPARK);
            sparkles.setParticleType(BroomsParticles.BROOM_SPARKLE);
            sparkles.setDuration(20 * 30);
            sparkles.setWaitTime(20 * 2);

            serverWorld.spawnEntity(sparkles);
        }

        for(int k = 0; k < j; ++k) {
            world.addParticle(blockStateParticleEffect, vec3d.x - (double)(direction == Direction.WEST ? 1.0E-6F : 0.0F), vec3d.y, vec3d.z - (double)(direction == Direction.NORTH ? 1.0E-6F : 0.0F), dustParticlesOffset.xd() * (double)i * 3.0D * world.getRandom().nextDouble(), 0.0D, dustParticlesOffset.zd() * (double)i * 3.0D * world.getRandom().nextDouble());
        }

    }

    static {
        MAX_SWEEP_DISTANCE = Math.sqrt(ServerPlayNetworkHandler.MAX_BREAK_SQUARED_DISTANCE) - 1.0D;
    }

    private record DustParticlesOffset(double xd, double yd, double zd) {
        public static BroomItem.DustParticlesOffset fromSide(Vec3d userRotation, Direction side) {
            return switch (side) {
                case DOWN, UP -> new DustParticlesOffset(userRotation.getZ(), 0.0D, -userRotation.getX());
                case NORTH -> new DustParticlesOffset(1.0D, 0.0D, -0.1D);
                case SOUTH -> new DustParticlesOffset(-1.0D, 0.0D, 0.1D);
                case WEST -> new DustParticlesOffset(-0.1D, 0.0D, -1.0D);
                case EAST -> new DustParticlesOffset(0.1D, 0.0D, 1.0D);
            };
        }

        public double xd() {
            return this.xd;
        }

        public double yd() {
            return this.yd;
        }

        public double zd() {
            return this.zd;
        }
    }

    public void setComponents(ItemStack stack, BroomRecipe.BroomPartCollection collection) {
        var nbt = stack.getOrCreateNbt();

        var shaftCompound = new NbtCompound();
        collection.shaft.writeNbt(shaftCompound);
        var joinerCompound = new NbtCompound();
        collection.joiner.writeNbt(joinerCompound);
        var brushCompound = new NbtCompound();
        collection.brush.writeNbt(brushCompound);

        nbt.put("shaft", shaftCompound);
        nbt.put("joiner", joinerCompound);
        nbt.put("brush", brushCompound);
    }
}
