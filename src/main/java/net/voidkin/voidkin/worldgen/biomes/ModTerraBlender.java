package net.voidkin.voidkin.worldgen.biomes;

import net.voidkin.voidkin.VoidkinMod;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;

public class ModTerraBlender {
    public static void registerBiomes() {
        Regions.register(new ModOverworldRegion(ResourceLocation.fromNamespaceAndPath(VoidkinMod.MODID, "overworld"), 5));
    }
}
