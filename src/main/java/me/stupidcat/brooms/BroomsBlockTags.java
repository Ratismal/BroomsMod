package me.stupidcat.brooms;

import com.mojang.datafixers.types.templates.Tag;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class BroomsBlockTags {
    public static final TagKey<Block> SWEEPABLE = TagKey.of(RegistryKeys.BLOCK, new Identifier("brooms", "sweepable"));
    public static final TagKey<Block> SWEEPABLE_DESTROY = TagKey.of(RegistryKeys.BLOCK, new Identifier("brooms", "sweepable_destroy"));
}
