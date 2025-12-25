package net.voidkin.voidkin.world;

import net.voidkin.voidkin.VoidkinMod;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidkin.voidkin.world.features.DarkStonePillars;

public class DeathFeatures{
public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, VoidkinMod.MODID);

public static final RegistryObject<DarkStonePillars> DARK_STONE_PILLAR = FEATURES.register("dark_stone_pillar", () -> new DarkStonePillars(NoneFeatureConfiguration.CODEC));


}