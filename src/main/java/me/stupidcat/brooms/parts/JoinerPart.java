package me.stupidcat.brooms.parts;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.util.Identifier;

public class JoinerPart extends BroomPart {
    public JoinerPart(Identifier sprite) {
        super(sprite);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public ModelPartData createPartModel(ModelPartData root) {
        return root.addChild("joiner", ModelPartBuilder.create()
                        .uv(0,0).cuboid( 7f, 5f, 7f, 2f, 2f, 2f, new Dilation(0f)),
                ModelTransform.pivot(0, 0, 0));
    }
}
