package net.voidkin.voidkin.item.curios;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.voidkin.voidkin.VoidkinMod.MODID;

public class VoidkinCurios {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

   public static final RegistryObject<Item> AXEL_RING = ITEMS.register("axel_ring", ()-> new Axel_Ring(new Item.Properties().stacksTo(1)));

}
