package me.stupidcat.brooms.client.particle;

import me.stupidcat.brooms.Brooms;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.math.MathHelper;
import org.joml.Vector3f;

@Environment(value= EnvType.CLIENT)
public class BroomSparkleParticle extends SpriteBillboardParticle {

    private final Vector3f START_COLOR = new Vector3f(0.5f, 0.5f, 0.5f);
    private final Vector3f END_COLOR = new Vector3f(1.0f, 1.0f, 1.0f);

    BroomSparkleParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
        super(clientWorld, d, e, f);
        this.collidesWithWorld = false;
        this.velocityY = h + (double) MathHelper.nextBetween(this.random, 0, 0.1f);
        this.velocityX = g + (double) MathHelper.nextBetween(this.random, -0.1f, 0.1f);
        this.velocityZ = i + (double)MathHelper.nextBetween(this.random, -0.1f, 0.1f);
        double j = Math.random() * 2.0;
        double k = Math.sqrt(this.velocityX * this.velocityX + this.velocityY * this.velocityY + this.velocityZ * this.velocityZ);
        this.velocityY = this.velocityY / k * j * (double)0.4f;
        this.velocityX = this.velocityX / k * j * (double)0.4f;
        this.velocityZ = this.velocityZ / k * j * (double)0.4f;
        this.scale *= 0.8f;
        this.velocityY *= (double)0.12f;
        this.velocityX *= (double)0.004f;
        this.velocityZ *= (double)0.004f;
        this.velocityY = Math.max(0, this.velocityY);
        this.maxAge = 18 + this.random.nextInt(4);
    }

    @Override
    public void buildGeometry(VertexConsumer vertexConsumer, Camera camera, float tickDelta) {
        this.lerpColor(tickDelta);
        super.buildGeometry(vertexConsumer, camera, tickDelta);
    }

    private void lerpColor(float tickDelta) {
        float f = ((float)this.age + tickDelta) / (float)(this.maxAge + 1);
        Vector3f vector3f = new Vector3f(this.START_COLOR).lerp(this.END_COLOR, f);
        this.red = vector3f.x();
        this.green = vector3f.y();
        this.blue = vector3f.z();
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void tick() {
        if (this.age++ >= this.maxAge) {
            this.markDead();
            return;
        }
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;
        this.move(this.velocityX, this.velocityY, this.velocityZ);
        this.velocityY *= 0.90;
        this.velocityX *= 0.90;
        this.velocityY *= 0.90;
    }

    @Environment(value=EnvType.CLIENT)
    public static class Factory
            implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            BroomSparkleParticle particle = new BroomSparkleParticle(clientWorld, d, e, f, g, h, i);
            particle.setSprite(this.spriteProvider);
            return particle;
        }
    }
}
