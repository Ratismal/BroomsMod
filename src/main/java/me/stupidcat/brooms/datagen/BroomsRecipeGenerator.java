package me.stupidcat.brooms.datagen;

import me.stupidcat.brooms.BroomsItems;
import me.stupidcat.brooms.item.ShaftItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.recipe.book.RecipeCategory;

public class BroomsRecipeGenerator extends FabricRecipeProvider {
    public BroomsRecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        for (var item : BroomsItems.SHAFTS) {
            var material = item.getMaterial();
            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, item)
                    .pattern("W  ")
                    .pattern(" W ")
                    .pattern("  W")
                    .input('W', material)
                    .criterion(FabricRecipeProvider.hasItem(material), FabricRecipeProvider.conditionsFromItem(material))
                    .offerTo(exporter);
        }

        for (var item : BroomsItems.JOINERS) {
            var material = item.getMaterial();
            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, item)
                    .pattern(" W ")
                    .pattern("W W")
                    .pattern(" W ")
                    .input('W', material)
                    .criterion(FabricRecipeProvider.hasItem(material), FabricRecipeProvider.conditionsFromItem(material))
                    .offerTo(exporter);
        }

        for (var item : BroomsItems.BRUSHES) {
            var material = item.getMaterial();
            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, item)
                    .pattern(" W ")
                    .pattern("WWW")
                    .pattern("WWW")
                    .input('W', material)
                    .criterion(FabricRecipeProvider.hasItem(material), FabricRecipeProvider.conditionsFromItem(material))
                    .offerTo(exporter);
        }
    }
}
