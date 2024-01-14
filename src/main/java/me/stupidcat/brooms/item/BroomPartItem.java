package me.stupidcat.brooms.item;

import me.stupidcat.brooms.parts.BroomPart;
import net.minecraft.item.Item;

public class BroomPartItem extends Item {
    private BroomPart broomPart;
    private Item material;
    public BroomPartItem(Settings settings, BroomPart part, Item material) {
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
