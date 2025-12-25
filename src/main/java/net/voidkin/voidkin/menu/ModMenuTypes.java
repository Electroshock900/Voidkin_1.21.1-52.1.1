package net.voidkin.voidkin.menu;

import net.voidkin.voidkin.VoidkinMod;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidkin.voidkin.menu.screens.custom.WarTortoiseHybridMenu;
import net.voidkin.voidkin.menu.screens.custom.WarTortoiseMenu;
import net.voidkin.voidkin.menu.screens.custom.WarTurtleMenu;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, VoidkinMod.MODID);

    public static final RegistryObject<MenuType<PolisherMenu>> POLISHING_MENU =
            registerMenuType("polisher_menu", PolisherMenu::new);

    public static final RegistryObject<MenuType<CrystallizerMenu>> CRYSTALLIZER_MENU =
            registerMenuType("crystallizer_menu", CrystallizerMenu::new);
    /*public static final RegistryObject<MenuType<DomMenu>> DOM_MENU =
            registerMenuType("dom_menu", DomMenu::new);
    public static final RegistryObject<MenuType<SubMenu>> SUB_MENU =
            registerMenuType("sub_menu", SubMenu::new);*/

    public static final RegistryObject<MenuType<VoidAltarMenu>> VOID_ALTAR_MENU =
            registerMenuType("void_altar_menu", VoidAltarMenu::new);
    public static final RegistryObject<MenuType<VoidPedestalMenu>> VOID_PEDESTAL_MENU =
            registerMenuType("void_pedestal_menu", VoidPedestalMenu::new);

    public static final RegistryObject<MenuType<PedestalMenu>> PEDESTAL_MENU =
            registerMenuType("pedestal_menu", PedestalMenu::new);


    public static final RegistryObject<MenuType<WarTurtleMenu>> WAR_TURTLE_MENU =
            registerMenuType("war_turtle", WarTurtleMenu::create);
    public static final RegistryObject<MenuType<WarTortoiseMenu>> WAR_TORTOISE_MENU =
            registerMenuType("war_tortoise", WarTortoiseMenu::create);
    public static final RegistryObject<MenuType<WarTortoiseHybridMenu>> WAR_TORTOISE_HYBRID_MENU =
            registerMenuType("war_tortoise_hybrid", WarTortoiseHybridMenu::create);

/*    public static final RegistryObject<MenuType<AltarBMenu>> ALTAR_B_MENU =
            registerMenuType("altar_b_menu", AltarBMenu::new);*/




    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}

