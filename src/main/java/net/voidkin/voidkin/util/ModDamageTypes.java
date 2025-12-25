package net.voidkin.voidkin.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageType;
import net.voidkin.voidkin.VoidkinMod;

import static net.voidkin.voidkin.util.ModDamageSources.LIFE_STEAL;

public interface ModDamageTypes {

    /*
     * Store the RegistryKey of our DamageType into a new constant called CUSTOM_DAMAGE_TYPE
     * The Identifier in use here points to our JSON file we created earlier.
     */
    public static final DamageType LIFE_STEAL = new DamageType(VoidkinMod.MODID + "lifesteal", DamageScaling.ALWAYS,2f, DamageEffects.HURT);
    ResourceKey<DamageType> LIFE_STEALS = ResourceKey.create(Registries.DAMAGE_TYPE,ResourceLocation.fromNamespaceAndPath(VoidkinMod.MODID,"life_steals"));
    ResourceKey<DamageType> CHAKRAMS = ResourceKey.create(Registries.DAMAGE_TYPE,ResourceLocation.fromNamespaceAndPath(VoidkinMod.MODID,"chakrams"));

    private static ResourceKey<DamageType> register(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(VoidkinMod.MODID, name));
    }
    static void bootstrap(BootstrapContext<DamageType> pContext) {
        pContext.register(LIFE_STEALS, new DamageType("life_steals", 2.0F, DamageEffects.HURT));
        pContext.register(CHAKRAMS, new DamageType("chakrams", 0.3F, DamageEffects.HURT));
    }
    }
/**
    public static DamageSource of(Level world, ResourceKey<DamageType> key) {
        return new DamageSource(world.getRegistryManager().get(Registries.DAMAGE_TYPE).entryOf(key));
    }**/

