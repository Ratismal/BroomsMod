package me.stupidcat.brooms.recipes;

import me.stupidcat.brooms.BroomsItems;
import me.stupidcat.brooms.BroomsRecipes;
import me.stupidcat.brooms.datagen.BroomsItemTagGenerator;
import me.stupidcat.brooms.item.BroomItem;
import me.stupidcat.brooms.item.BrushItem;
import me.stupidcat.brooms.item.JoinerItem;
import me.stupidcat.brooms.item.ShaftItem;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.TippedArrowRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.world.World;

public class BroomRecipe extends SpecialCraftingRecipe {
    public BroomRecipe(CraftingRecipeCategory category) {
        super(category);
    }

    public static class BroomPartCollection {
        public ItemStack shaft = null;
        public ItemStack joiner = null;
        public ItemStack brush = null;

        Boolean valid = false;

        public BroomPartCollection(ItemStack stack) {
            var nbt = stack.getOrCreateNbt();
            shaft = ItemStack.fromNbt(nbt.getCompound("shaft"));
            joiner = ItemStack.fromNbt(nbt.getCompound("joiner"));
            brush = ItemStack.fromNbt(nbt.getCompound("brush"));

            if (!(shaft.getItem() instanceof ShaftItem)) {
                shaft = BroomsItems.SHAFTS.get(0).getDefaultStack();
            }
            if (!(joiner.getItem() instanceof JoinerItem)) {
                joiner = BroomsItems.JOINERS.get(0).getDefaultStack();
            }
            if (!(brush.getItem() instanceof BrushItem)) {
                brush = BroomsItems.BRUSHES.get(0).getDefaultStack();
            }
        }

        BroomPartCollection(RecipeInputInventory inventory) {
            for (var stack : inventory.getHeldStacks()) {
                Item item = stack.getItem();
                if (item instanceof ShaftItem && shaft == null) {
                    shaft = stack.copy();
                } else if (item instanceof JoinerItem && joiner == null) {
                    joiner = stack.copy();
                } else if (item instanceof BrushItem && brush == null) {
                    brush = stack.copy();
                } else if (!stack.isOf(Items.AIR)) {
                    valid = false;
                    return;
                }
            }

            valid = shaft != null && joiner != null && brush != null;
        }

        public Boolean isValid() {
            return valid;
        }
    }

    @Override
    public boolean matches(RecipeInputInventory inventory, World world) {
        var collection = new BroomPartCollection(inventory);

        return collection.isValid();
    }

    @Override
    public ItemStack craft(RecipeInputInventory inventory, DynamicRegistryManager registryManager) {
        var collection = new BroomPartCollection(inventory);

        var item = BroomsItems.BROOM.getDefaultStack();

        var broomItem = (BroomItem) BroomsItems.BROOM;
        broomItem.setComponents(item, collection);

        return item;
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= 3;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return BroomsRecipes.BROOM_SERIALIZER;
    }
}
