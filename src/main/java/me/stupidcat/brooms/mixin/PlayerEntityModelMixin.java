package me.stupidcat.brooms.mixin;

import me.stupidcat.brooms.Brooms;
import me.stupidcat.brooms.BroomsItems;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntityModel.class)
public abstract class PlayerEntityModelMixin extends BipedEntityModel<PlayerEntity> {
    public PlayerEntityModelMixin(ModelPart root) {
        super(root);
    }

    @Shadow
    @Final
    public ModelPart leftSleeve;
    @Shadow
    @Final
    public ModelPart rightSleeve;

    @Override
    public void animateModel(PlayerEntity livingEntity, float f, float g, float h) {
        // Brooms.LOGGER.info("h");
        super.animateModel(livingEntity, f, g, h);
    }

    protected void animatePlayerArms(LivingEntity entity, float animationProgress) {
        if (entity.getActiveItem().isOf(BroomsItems.BROOM)) {
            if (rightArmPose == ArmPose.BRUSH) {
                this.rightArm.yaw = MathHelper.sin(animationProgress / 2f) / 2f * this.rightArm.yaw - 0.5f;
                // this.rightArm.pitch = MathHelper.sin(animationProgress / 2f) / -10f - 1.2f;
                this.rightArm.pitch = -1.4f;
                this.rightArm.roll = MathHelper.sin(animationProgress / 2f) / 2f;

                this.leftArm.yaw = MathHelper.sin(animationProgress / 2f) / 2f * -this.leftArm.yaw + 0.6f;
                this.leftArm.pitch = -1f;
                this.leftArm.roll = 0f;
            } else if (leftArmPose == ArmPose.BRUSH) {
                this.leftArm.yaw = MathHelper.sin(animationProgress / 2f) / 2f * -this.leftArm.yaw + 0.5f;
                // this.rightArm.pitch = MathHelper.sin(animationProgress / 2f) / -10f - 1.2f;
                this.leftArm.pitch = -1.4f;
                this.leftArm.roll = MathHelper.sin(animationProgress / 2f) / 2f;

                this.rightArm.yaw = MathHelper.sin(animationProgress / 2f) / 2f * this.rightArm.yaw - 0.6f;
                this.rightArm.pitch = -1f;
                this.rightArm.roll = 0f;
            }
        }
    }

    @Inject(method = "setAngles(Lnet/minecraft/entity/LivingEntity;FFFFF)V", at = @At("TAIL"))
    public void injected(LivingEntity livingEntity, float f, float g, float h, float i, float j, CallbackInfo ci) {
        if (livingEntity.getActiveItem().isOf(BroomsItems.BROOM)) {
            this.rightArm.pitch = this.rightArm.pitch   * 0.5f - 1f;
            this.rightArm.yaw   = this.rightArm.yaw     * 0.5f - 1f;
            this.leftArm.pitch  = this.leftArm.pitch    * 0.5f - 0.7f;
            this.leftArm.yaw    = this.leftArm.yaw      * 0.5f + 0.7f;

            this.animatePlayerArms(livingEntity, h);


            this.leftSleeve.copyTransform(this.leftArm);
            this.rightSleeve.copyTransform(this.rightArm);
        }
    }
}
