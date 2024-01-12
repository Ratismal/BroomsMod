package me.stupidcat.brooms;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BroomsParticles {
    public static final DefaultParticleType BROOM_DUST = register("broom_dust", false);
    public static final DefaultParticleType BROOM_SPARKLE = register("broom_sparkle", false);

    private static DefaultParticleType register(String name, boolean alwaysShow) {
        return Registry.register(Registries.PARTICLE_TYPE, new Identifier("brooms", name), FabricParticleTypes.simple(alwaysShow));
    }

    public static void register() {
        // Intentionally left blank
    }
}
