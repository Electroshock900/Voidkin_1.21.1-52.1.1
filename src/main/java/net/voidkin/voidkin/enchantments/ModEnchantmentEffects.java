package net.voidkin.voidkin.enchantments;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.item.enchantment.effects.EnchantmentLocationBasedEffect;
import net.minecraft.world.item.enchantment.effects.ReplaceDisk;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.voidkin.voidkin.VoidkinMod;

public class ModEnchantmentEffects {
    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENTITY_ENCHANTMENT_EFFECTS =
            DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, VoidkinMod.MODID);

    public static final RegistryObject<MapCodec<? extends EnchantmentEntityEffect>> LIGHTNING_STRIKER =
            ENTITY_ENCHANTMENT_EFFECTS.register("lightning_striker", () -> LightningStrikerEnchantment.CODEC);

    public static final DeferredRegister<MapCodec<? extends EnchantmentLocationBasedEffect>> LOCATION_BASED_ENCHANTMENT_EFFECTS =
            DeferredRegister.create(Registries.ENCHANTMENT_LOCATION_BASED_EFFECT_TYPE, VoidkinMod.MODID);

    public static final RegistryObject<MapCodec<? extends EnchantmentLocationBasedEffect>> BLOOD_WALKER =
            LOCATION_BASED_ENCHANTMENT_EFFECTS.register("blood_walker", ()-> ReplaceDisk.CODEC);



    public static void register(IEventBus eventBus) {
        ENTITY_ENCHANTMENT_EFFECTS.register(eventBus);
    }
}
