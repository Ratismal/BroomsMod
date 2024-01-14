package me.stupidcat.brooms.parts;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;

public class BroomJoinerPart extends BroomPart {
    @Override
    @Environment(EnvType.CLIENT)
    public ModelPartData createPartModel(ModelPartData root) {
        return root.addChild("shaft", ModelPartBuilder.create()
                        .uv(0, 0).cuboid( 7.5f, 18f, 7.5f, 1f, 12f, 1f, new Dilation(0f))
                        .uv(4, 0).cuboid(7.5f, 6f, 7.5f, 1f, 12f, 1f, new Dilation(0f)),
                ModelTransform.pivot(0, 0, 0));
    }
}
