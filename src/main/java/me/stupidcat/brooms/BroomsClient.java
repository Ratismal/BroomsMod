package me.stupidcat.brooms;

import me.stupidcat.brooms.client.particle.BroomDustParticle;
import me.stupidcat.brooms.client.particle.BroomSparkleParticle;
import me.stupidcat.brooms.client.renderer.BroomItemRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.particle.GustDustParticle;
import net.minecraft.client.render.block.entity.BannerBlockEntityRenderer;
import net.minecraft.client.render.entity.EmptyEntityRenderer;
import net.minecraft.client.render.entity.EntityRenderers;
import net.minecraft.entity.EntityType;
import net.minecraft.screen.PlayerScreenHandler;

public class BroomsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ParticleFactoryRegistry.getInstance().register(BroomsParticles.BROOM_DUST, BroomDustParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(BroomsParticles.BROOM_SPARKLE, BroomSparkleParticle.Factory::new);
        BuiltinItemRendererRegistry.INSTANCE.register(BroomsItems.BROOM, new BroomItemRenderer());
        EntityRendererRegistry.register(BroomsEntities.SPARKLE_CLOUD, EmptyEntityRenderer::new);
    }
}