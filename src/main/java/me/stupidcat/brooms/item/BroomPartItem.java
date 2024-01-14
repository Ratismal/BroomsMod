package me.stupidcat.brooms.item;

import me.stupidcat.brooms.parts.BroomPart;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BroomPartItem extends Item {
    private final BroomPart broomPart;
    private final Item material;
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

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.brooms.part_tooltip")
                .setStyle(Style.EMPTY.withColor(TextColor.fromFormatting(Formatting.GRAY)).withItalic(true)));
    }
}
