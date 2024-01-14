package me.stupidcat.brooms.datagen;

import me.stupidcat.brooms.BroomsItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class BroomsModelGenerator extends FabricModelProvider {
    public BroomsModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        for (var shaft : BroomsItems.SHAFTS) {
            itemModelGenerator.register(shaft, Models.GENERATED);
        }
    }
}
