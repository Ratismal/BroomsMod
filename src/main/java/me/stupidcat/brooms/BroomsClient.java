package me.stupidcat.brooms;

import me.stupidcat.brooms.client.particle.BroomDustParticle;
import me.stupidcat.brooms.client.particle.BroomSparkleParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.particle.GustDustParticle;
import net.minecraft.client.render.entity.EmptyEntityRenderer;
import net.minecraft.client.render.entity.EntityRenderers;
import net.minecraft.entity.EntityType;
import net.minecraft.screen.PlayerScreenHandler;

public class BroomsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        /* Adds our particle textures to vanilla's Texture Atlas so it can be shown properly.
         * Modify the namespace and particle id accordingly.
         *
         * This is only used if you plan to add your own textures for the particle. Otherwise, remove  this.*/
        /*
        ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register(((atlasTexture, registry) -> {
            registry.register(new Identifier("modid", "particle/green_flame"));
        }));
         */

        /* Registers our particle client-side.
         * First argument is our particle's instance, created previously on ExampleMod.
         * Second argument is the particle's factory. The factory controls how the particle behaves.
         * In this example, we'll use FlameParticle's Factory.*/
        ParticleFactoryRegistry.getInstance().register(BroomsParticles.BROOM_DUST, BroomDustParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(BroomsParticles.BROOM_SPARKLE, BroomSparkleParticle.Factory::new);

        EntityRendererRegistry.register(BroomsEntities.SPARKLE_CLOUD, EmptyEntityRenderer::new);
    }
}