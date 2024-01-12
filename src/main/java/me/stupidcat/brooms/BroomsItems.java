package me.stupidcat.brooms;

import me.stupidcat.brooms.item.BroomItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BroomsItems {
    public static final Item BROOM = register("broom", new BroomItem(new FabricItemSettings().maxCount(1)));

    public static Item register(String id, Item item) {
        return Registry.register(Registries.ITEM, new Identifier("brooms", id), item);
    }
}
