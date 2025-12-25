package net.voidkin.voidkin.util;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidkin.voidkin.VoidkinMod;
import net.voidkin.voidkin.effect.ModEffects;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(ForgeRegistries.POTIONS, VoidkinMod.MODID);

    public static final RegistryObject<Potion> SPIDER_POTION = POTIONS.register("spider_potion",
            ()-> new Potion(new MobEffectInstance(ModEffects.SPIDER_EFFECT.getHolder().get(),200,2)));

    public static void register(IEventBus eventBus){
        POTIONS.register(eventBus);
    }
}
