package me.stupidcat.brooms.mixin;

import me.stupidcat.brooms.BroomsItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HeldItemRenderer.class)
public abstract class HeldItemRendererMixin {

    @Shadow
    @Final
    private MinecraftClient client;

    @Shadow public abstract void renderItem(LivingEntity entity, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light);

    @Inject(method = "renderFirstPersonItem(Lnet/minecraft/client/network/AbstractClientPlayerEntity;FFLnet/minecraft/util/Hand;FLnet/minecraft/item/ItemStack;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At("HEAD"), cancellable = true)
    private void renderFirstPersonItem(AbstractClientPlayerEntity player, float tickDelta, float pitch, Hand hand, float swingProgress, ItemStack item, float equipProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        if (item.isOf(BroomsItems.BROOM)) {
            if (player.isUsingItem() && player.getItemUseTimeLeft() > 0 && player.getActiveHand() == hand) {

                boolean bl = hand == Hand.MAIN_HAND;
                Arm arm = bl ? player.getMainArm() : player.getMainArm().getOpposite();
                boolean bl3 = arm == Arm.RIGHT;

                matrices.push();
                this.applyBroomTransformation(matrices, tickDelta, arm, item, equipProgress);

                this.renderItem(player, item, bl3 ? ModelTransformationMode.FIRST_PERSON_RIGHT_HAND : ModelTransformationMode.FIRST_PERSON_LEFT_HAND, !bl3, matrices, vertexConsumers, light);

                matrices.pop();

                ci.cancel();
            }
        }
    }

    private void applyEquipOffset(MatrixStack matrices, Arm arm, float equipProgress) {
        int i = arm == Arm.RIGHT ? 1 : -1;
        matrices.translate((float)i * 0.56f, -0.52f + equipProgress * -0.6f, -0.72f);
    }

    private void applyBroomTransformation(MatrixStack matrices, float tickDelta, Arm arm, ItemStack stack, float equipProgress) {
        this.applyEquipOffset(matrices, arm, equipProgress);
        float f = this.client.player.getItemUseTimeLeft() % 10;
        float g = f - tickDelta + 1.0f;
        float h = 1.0f - g / 10.0f;
        float n = -15.0f + 15.0f * MathHelper.cos(h * 2.0f * (float)Math.PI);

        if (arm != Arm.RIGHT) {
            matrices.translate(-0.25, 0.22, -0.35);

            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(120.0f));
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(90.0f));
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(n), 0f, 1f, 0f);
        } else {
            matrices.translate(-0, 0.22, -0.35);

            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(120.0f));
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(90.0f));
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(n + 15f), 0f, 1f, 0f);
        }
    }
}