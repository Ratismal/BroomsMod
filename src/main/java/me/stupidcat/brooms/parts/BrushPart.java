package me.stupidcat.brooms.parts;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.util.Identifier;

public class BrushPart extends BroomPart {
    public BrushPart(Identifier sprite) {
        super(sprite);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public ModelPartData createPartModel(ModelPartData root) {
        var brush = root.addChild("brush", ModelPartBuilder.create(),
                ModelTransform.pivot(0, 0, 0));

        addBristle(brush, 0f, "bristle_1");
        addBristle(brush, 45f, "bristle_2");
        addBristle(brush, 90f, "bristle_3");
        addBristle(brush, 135f, "bristle_4");
        addBristle(brush, 0f + 22.5f, "bristle_5");
        addBristle(brush, 45f + 22.5f, "bristle_6");
        addBristle(brush, 90f + 22.5f, "bristle_7");
        addBristle(brush, 135f + 22.5f, "bristle_8");

        return brush;
    }

    private void addBristle(ModelPartData root, float angle, String name) {
        var transform = ModelTransform.of(8f, 0f, 8f, (float)Math.toRadians(180), (float)Math.toRadians(angle), 0f);
        root.addChild(name, ModelPartBuilder.create()
                        .uv(0, 0).cuboid(-3f, -6f, 0f, 6f, 7f, 0f, new Dilation(0f)), transform);
    }
}
