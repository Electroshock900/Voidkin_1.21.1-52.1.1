package net.voidkin.voidkin.util;

import net.voidkin.voidkin.VoidkinMod;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWoodTypes {
    public static final WoodType DARK = WoodType.register(new WoodType(VoidkinMod.MODID + ":dark", BlockSetType.DARK_OAK));
    public static final WoodType BLOOD = WoodType.register(new WoodType(VoidkinMod.MODID + ":blood", BlockSetType.DARK_OAK));
    public static final WoodType VOID = WoodType.register(new WoodType(VoidkinMod.MODID + ":void", BlockSetType.DARK_OAK));
}