package me.stupidcat.brooms.entity;

import me.stupidcat.brooms.BroomsEntities;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.world.World;

public class SparkleCloudEntity extends AreaEffectCloudEntity {
    public SparkleCloudEntity(EntityType<? extends AreaEffectCloudEntity> entityType, World world) {
        super(entityType, world);
        this.noClip = true;
    }

    public static SparkleCloudEntity Create(EntityType<? extends AreaEffectCloudEntity> entityType, World world) {
        return new SparkleCloudEntity(entityType, world);
    }

    public SparkleCloudEntity(World world, double x, double y, double z) {
        this(BroomsEntities.SPARKLE_CLOUD, world);
        this.setPosition(x, y, z);
    }

    @Override
    public void tick() {
        boolean isWaiting = this.isWaiting();
        float radius = this.getRadius();

        if (this.age >= this.getWaitTime() + this.getDuration()) {
            this.discard();
            return;
        }

        boolean shouldStillWait = this.age < this.getWaitTime();
        if (isWaiting != shouldStillWait) {
            this.setWaiting(shouldStillWait);
        }

        if (shouldStillWait) return;

        if (this.getWorld().isClient) {
            if (isWaiting && this.random.nextBoolean()) {
                return;
            }

            if (this.age % 40 != 0) {
                return;
            }

            ParticleEffect particleEffect = this.getParticleType();
            int particles = this.random.nextBetween(2, 4);
            if (radius < 1) particles = 1;

            for (int j = 0; j < particles; ++j) {
                double d = this.getX() + this.random.nextFloat() * radius * 2 - radius;
                double e = this.getY();
                double l = this.getZ() + this.random.nextFloat() * radius * 2 - radius;
                double n = (0.5 - this.random.nextDouble()) * 0.15;
                double o = 0.01f;
                double p = (0.5 - this.random.nextDouble()) * 0.15;

                this.getWorld().addImportantParticle(particleEffect, d, e, l, n, o, p);
            }
        }
    }
}
