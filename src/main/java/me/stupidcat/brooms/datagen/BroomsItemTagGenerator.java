package me.stupidcat.brooms.datagen;

import me.stupidcat.brooms.BroomsItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.data.server.tag.ItemTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class BroomsItemTagGenerator extends FabricTagProvider.ItemTagProvider {
    public BroomsItemTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture, BlockTagProvider blockTagProvider) {
        super(output, registriesFuture, blockTagProvider);
    }

    public static final TagKey<Item> SHAFT_ITEMS = TagKey.of(RegistryKeys.ITEM, new Identifier("brooms", "shafts"));
    public static final TagKey<Item> JOINER_ITEMS = TagKey.of(RegistryKeys.ITEM, new Identifier("brooms", "joiners"));
    public static final TagKey<Item> BRUSH_ITEMS = TagKey.of(RegistryKeys.ITEM, new Identifier("brooms", "brushes"));

    @Override
    public void configure(RegistryWrapper.WrapperLookup lookup) {
        var shafts = getOrCreateTagBuilder(SHAFT_ITEMS);
        for (var item : BroomsItems.SHAFTS) {
            shafts.add(item);
        }

        var joiners = getOrCreateTagBuilder(JOINER_ITEMS);
        for (var item : BroomsItems.JOINERS) {
            joiners.add(item);
        }

        var brushes = getOrCreateTagBuilder(BRUSH_ITEMS);
        for (var item : BroomsItems.BRUSHES) {
            brushes.add(item);
        }
    }
}
