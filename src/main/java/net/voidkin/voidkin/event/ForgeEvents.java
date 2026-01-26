package net.voidkin.voidkin.event;


import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.brewing.BrewingRecipeRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.voidkin.voidkin.VoidkinMod;
import net.voidkin.voidkin.block.custom.CongealedBloodBlock;
import net.voidkin.voidkin.enchantments.ModEnchantments;
import net.voidkin.voidkin.util.ModPotions;

import static net.minecraft.world.level.block.Blocks.LAVA;
import static net.voidkin.voidkin.block.ModBlocks.*;


@Mod.EventBusSubscriber(modid= VoidkinMod.MODID,bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEvents {


    @SubscribeEvent
    public static void onBrewingRecipeRegister(BrewingRecipeRegisterEvent event){
        PotionBrewing.Builder builder = event.getBuilder();
        builder.addMix(Potions.AWKWARD, Items.FERMENTED_SPIDER_EYE, ModPotions.SPIDER_POTION.getHolder().get());
    }
}