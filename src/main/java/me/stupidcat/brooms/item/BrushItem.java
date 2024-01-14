package me.stupidcat.brooms.item;

import me.stupidcat.brooms.parts.BroomPart;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;

public class JoinerItem extends Item {
    private BroomPart broomPart;
    private Item material;
    public JoinerItem(Settings settings, BroomPart part, Item material) {
        super(settings);
        broomPart = part;
        this.material = material;
    }

    public BroomPart getBroomPart() {
        return broomPart;
    }

    public Item getMaterial() {
        return material;
    }
}
