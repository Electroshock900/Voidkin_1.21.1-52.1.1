package net.voidkin.voidkin.entity;

import net.voidkin.voidkin.VoidkinMod;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = VoidkinMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ChestsEntities {

        private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
                DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, VoidkinMod.MODID);

        //<editor-fold desc="Chests">


    public static void registerBlockEntities() {
        //noinspection removal
        BLOCK_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
