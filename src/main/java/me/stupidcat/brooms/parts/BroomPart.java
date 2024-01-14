package me.stupidcat.brooms.parts;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.util.Identifier;

public class BroomPart {
    Identifier sprite;

    public BroomPart(Identifier sprite) {
        this.sprite = sprite;
    }

    private ModelPartData modelPartData = null;

    @Environment(EnvType.CLIENT)
    public ModelPartData createPartModel(ModelPartData root) {
        return null;
    }

    @Environment(EnvType.CLIENT)
    public ModelPartData getModelPartData() {
        if (modelPartData == null) modelPartData = createPartModel(new ModelData().getRoot());
        return modelPartData;
    }

    public Identifier getSprite() {
        return sprite;
    }
}
