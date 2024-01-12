package me.stupidcat.brooms;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class BroomsItemGroups {
    public static final ItemGroup BROOMS_ITEMS = register("brooms_items", FabricItemGroup.builder()
            .icon(() -> new ItemStack(Items.SHULKER_BOX))
            .displayName(Text.translatable("itemGroup.brooms.brooms_items"))
            .entries((context, entries) -> {
                entries.add(BroomsItems.BROOM);
            })
            .build()
    );

    public static ItemGroup register(String id, ItemGroup group) {
        return Registry.register(Registries.ITEM_GROUP, RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier("brooms", id)), group);
    }

    public static void register() {
        // Intentionally left blank
    }
}
