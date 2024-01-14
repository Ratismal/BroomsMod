package me.stupidcat.brooms;

import me.stupidcat.brooms.recipes.BroomRecipe;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BroomsRecipes {
    public static final SpecialRecipeSerializer<BroomRecipe> BROOM_SERIALIZER = new SpecialRecipeSerializer<>(BroomRecipe::new);

    public static void register() {
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier("brooms", "broom"), BROOM_SERIALIZER);
    }
}
