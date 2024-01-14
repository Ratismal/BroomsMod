package me.stupidcat.brooms;

import me.stupidcat.brooms.item.BroomItem;
import me.stupidcat.brooms.item.BrushItem;
import me.stupidcat.brooms.item.JoinerItem;
import me.stupidcat.brooms.item.ShaftItem;
import me.stupidcat.brooms.parts.BroomPart;
import me.stupidcat.brooms.parts.BrushPart;
import me.stupidcat.brooms.parts.JoinerPart;
import me.stupidcat.brooms.parts.ShaftPart;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class BroomsItems {
    public static final Item BROOM = register("broom", new BroomItem(new FabricItemSettings().maxCount(1)));

    // Shafts
    public static final List<ShaftItem> SHAFTS = new ArrayList<>();
    public static final List<JoinerItem> JOINERS = new ArrayList<>();
    public static final List<BrushItem> BRUSHES = new ArrayList<>();

    static {
        registerShaft("oak", Items.OAK_PLANKS, new ShaftPart(Brooms.Id("item/parts/shaft_oak")));
        registerShaft("acacia", Items.ACACIA_PLANKS, new ShaftPart(Brooms.Id("item/parts/shaft_acacia")));
        registerShaft("bamboo", Items.BAMBOO, new ShaftPart(Brooms.Id("item/parts/shaft_bamboo")));
        registerShaft("birch", Items.BIRCH_PLANKS, new ShaftPart(Brooms.Id("item/parts/shaft_birch")));
        registerShaft("cherry", Items.CHERRY_PLANKS, new ShaftPart(Brooms.Id("item/parts/shaft_cherry")));
        registerShaft("crimson", Items.CRIMSON_PLANKS, new ShaftPart(Brooms.Id("item/parts/shaft_crimson")));
        registerShaft("dark_oak", Items.DARK_OAK_PLANKS, new ShaftPart(Brooms.Id("item/parts/shaft_dark_oak")));
        registerShaft("jungle", Items.JUNGLE_PLANKS, new ShaftPart(Brooms.Id("item/parts/shaft_jungle")));
        registerShaft("mangrove", Items.MANGROVE_PLANKS, new ShaftPart(Brooms.Id("item/parts/shaft_mangrove")));
        registerShaft("spruce", Items.SPRUCE_PLANKS, new ShaftPart(Brooms.Id("item/parts/shaft_spruce")));
        registerShaft("warped", Items.WARPED_PLANKS, new ShaftPart(Brooms.Id("item/parts/shaft_warped")));

        registerJoiner("string", Items.STRING, new JoinerPart(Brooms.Id("item/parts/joiner_string")));
        registerJoiner("iron", Items.IRON_INGOT, new JoinerPart(Brooms.Id("item/parts/joiner_iron")));
        registerJoiner("gold", Items.GOLD_INGOT, new JoinerPart(Brooms.Id("item/parts/joiner_gold")));

        registerBrush("straw", Items.WHEAT, new BrushPart(Brooms.Id("item/parts/brush_straw")));
    }

    public static void registerShaft(String type, Item material, BroomPart part) {
        SHAFTS.add((ShaftItem) register("shaft_" + type, new ShaftItem(new FabricItemSettings(), part, material)));
    }
    public static void registerJoiner(String type, Item material, BroomPart part) {
        JOINERS.add((JoinerItem) register("joiner_" + type, new JoinerItem(new FabricItemSettings(), part, material)));
    }
    public static void registerBrush(String type, Item material, BroomPart part) {
        BRUSHES.add((BrushItem) register("brush_" + type, new BrushItem(new FabricItemSettings(), part, material)));
    }

    public static Item register(String id, Item item) {
        return Registry.register(Registries.ITEM, Brooms.Id(id), item);
    }
}
