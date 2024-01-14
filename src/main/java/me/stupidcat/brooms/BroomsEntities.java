package me.stupidcat.brooms;

import me.stupidcat.brooms.entity.SparkleCloudEntity;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BroomsEntities {
    public static final EntityType<SparkleCloudEntity> SPARKLE_CLOUD = register("sparkle_cloud", EntityType.Builder.create(SparkleCloudEntity::Create, SpawnGroup.MISC).makeFireImmune().setDimensions(6.0f, 0.5f).maxTrackingRange(10).trackingTickInterval(Integer.MAX_VALUE));

    private static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> type) {
        return Registry.register(Registries.ENTITY_TYPE, Brooms.Id(id), type.build(id));
    }

    public static void register() {
        // Intentionally left blank
    }
}
