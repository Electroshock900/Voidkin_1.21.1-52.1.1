package net.voidkin.voidkin.util;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.ForgeTier;
import net.voidkin.voidkin.item.ModItems;

public class ModMaterials {
    public static final Tier DARKNESS = new ForgeTier(1300,13.0F,6.5F,13,ModTags.Blocks.VOIDKIN_BLOCKS,()->Ingredient.of(ModItems.DARK_SHARD.get()),ModTags.Blocks.ANTI_VOID_BLOCKS);
    public static final Tier CACTUS = new ForgeTier(13,1300,3.0F,7,BlockTags.NEEDS_IRON_TOOL, ()->Ingredient.of(Blocks.CACTUS), BlockTags.INCORRECT_FOR_NETHERITE_TOOL);
}
