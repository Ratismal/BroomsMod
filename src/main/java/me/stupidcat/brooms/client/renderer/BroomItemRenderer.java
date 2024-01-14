package me.stupidcat.brooms.client.renderer;

import me.stupidcat.brooms.BroomsItems;
import me.stupidcat.brooms.item.BrushItem;
import me.stupidcat.brooms.item.JoinerItem;
import me.stupidcat.brooms.item.ShaftItem;
import me.stupidcat.brooms.recipes.BroomRecipe;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.SpriteTexturedVertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;

public class BroomItemRenderer implements BuiltinItemRendererRegistry.DynamicItemRenderer {
    private static class TextureData {
        public int width;
        public int height;
        public Identifier id;

        public TextureData(int width, int height, Identifier id) {
            this.width = width;
            this.height = height;
            this.id = id;
        }

        public Sprite getSprite() {
            return MinecraftClient.getInstance().getBakedModelManager().getAtlas(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).getSprite(id);
        }
    }

    private void renderPart(ModelPartData partData, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, TextureData textureData) {
        var part = partData.createPart(textureData.width, textureData.height);

        matrices.push();
        var layer = RenderLayer.getCutout();
        var consumer = vertexConsumers.getBuffer(layer);
        Sprite sprite = textureData.getSprite();
        var spriteConsumer = new SpriteTexturedVertexConsumer(consumer, sprite);
        part.render(matrices, spriteConsumer, light, overlay);

        matrices.pop();
    }

    @Override
    public void render(ItemStack stack, ModelTransformationMode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        ModelData modelData = new ModelData();

        var collection = new BroomRecipe.BroomPartCollection(stack);
        var shaftItem = (ShaftItem) collection.shaft.getItem();
        var joinerItem = (JoinerItem) collection.joiner.getItem();
        var brushItem = (BrushItem) collection.brush.getItem();

        var shaft = shaftItem.getBroomPart().getModelPartData();
        var joiner = joinerItem.getBroomPart().getModelPartData();
        var brush = brushItem.getBroomPart().getModelPartData();

        renderPart(shaft, matrices, vertexConsumers, light, overlay, new TextureData(16, 16, shaftItem.getBroomPart().getSprite()));
        renderPart(joiner, matrices, vertexConsumers, light, overlay, new TextureData(8, 8, joinerItem.getBroomPart().getSprite()));
        renderPart(brush, matrices, vertexConsumers, light, overlay, new TextureData(16, 16, brushItem.getBroomPart().getSprite()));
    }
}
