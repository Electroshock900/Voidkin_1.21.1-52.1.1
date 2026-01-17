package net.voidkin.voidkin.datagen;

import net.voidkin.voidkin.VoidkinMod;
import net.voidkin.voidkin.damage_types.ModDamageTypes;
import net.voidkin.voidkin.worldgen.ModBiomeModifiers;
import net.voidkin.voidkin.world.ModConfiguredFeatures;
import net.voidkin.voidkin.worldgen.ModPlacedFeatures;
//import net.voidkin.voidkin.worldgen.biomes.*;
import net.voidkin.voidkin.worldgen.biomes.ModBiomes;
import net.voidkin.voidkin.worldgen.dimension.ModDimensions;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModWorldGenProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.DIMENSION_TYPE, ModDimensions::bootstrapType)
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap)
            .add(Registries.BIOME, ModBiomes::boostrap)
            .add(Registries.LEVEL_STEM, ModDimensions::bootstrapStem);
            //.add(Registries.)
            //.add(Registries.DAMAGE_TYPE, ModDamageTypes::bootstrap);

    public ModWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(VoidkinMod.MODID));
    }
}