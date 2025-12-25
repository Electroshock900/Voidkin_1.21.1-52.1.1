package net.voidkin.voidkin.fluid;

import net.voidkin.voidkin.VoidkinMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.SoundAction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.joml.Vector3f;

public class ModFluidTypes {


    public static final ResourceLocation WATER_STILL_RL = ResourceLocation.withDefaultNamespace("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = ResourceLocation.withDefaultNamespace("block/water_flow");
    public static final ResourceLocation SOAP_OVERLAY_RL = ResourceLocation.fromNamespaceAndPath(VoidkinMod.MODID, "misc/in_soap_water");

    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, VoidkinMod.MODID);

    public static final RegistryObject<FluidType> SOAP_WATER_FLUID_TYPE2 = soap("soap_water_fluid4",
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK));
    public static final RegistryObject<FluidType> SOAP_WATER_FLUID_TYPE = FLUID_TYPES.register("soap_water_fluid", ()-> new BaseFluidType(
            WATER_STILL_RL,
            WATER_FLOWING_RL,
            ResourceLocation.fromNamespaceAndPath(VoidkinMod.MODID, "misc/in_soap_water"),
            0xA1E038D0,
            new Vector3f(224f / 255f, 56f / 255f, 208f / 255f),
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK)));
    public static final RegistryObject<FluidType> ENDER_FLUID_TYPE = FLUID_TYPES.register("ender_fluid",() -> new BaseFluidType(WATER_STILL_RL, WATER_FLOWING_RL, SOAP_OVERLAY_RL,
            0xA15f3298, new Vector3f(112f / 255f, 56f / 255f, 152f / 255f),
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.ENDERMAN_AMBIENT))
    );

    public static final RegistryObject<FluidType> BLOOD_FLUID_TYPE = FLUID_TYPES.register("blood_fluid",() -> new BaseFluidType(WATER_STILL_RL, WATER_FLOWING_RL, SOAP_OVERLAY_RL,
            0xC0381015, new Vector3f(56f / 255f, 16f / 255f, 21f / 255f),
            FluidType.Properties.create().density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK))
    );

    public static final RegistryObject<FluidType> DEITY_BLOOD_FLUID_TYPE = FLUID_TYPES.register("deity_blood_fluid",() -> new BaseFluidType(
            WATER_STILL_RL,
            WATER_FLOWING_RL,
            SOAP_OVERLAY_RL,
            0xD9ff6E, new Vector3f(217f / 255f, 255f / 255f, 110f / 255f),
            FluidType.Properties.create().lightLevel(13).viscosity(7).canConvertToSource(true).canHydrate(true).canDrown(true)
                    .sound(SoundAction.get("drink"),
                            SoundEvents.SCULK_SHRIEKER_SHRIEK))
    );
    public static final RegistryObject<FluidType> DARK_ESSENCE_FLUID_TYPE = FLUID_TYPES.register("dark_essence_fluid",
            ()-> new BaseFluidType(WATER_STILL_RL,WATER_FLOWING_RL,SOAP_OVERLAY_RL,
                    0x00bb00, new Vector3f(16f / 255f, 56f / 255f, 21f / 255f),
                    FluidType.Properties.create().lightLevel(13).density(3).canConvertToSource(true).sound(SoundAction.get("drink"), SoundEvents.WARDEN_LISTENING_ANGRY).fallDistanceModifier(-100))
    );
    public static final RegistryObject<FluidType> VOID_LAVA_FLUID_TYPE = FLUID_TYPES.register("void_lava_fluid",
            () -> new BaseFluidType(WATER_STILL_RL,WATER_FLOWING_RL,SOAP_OVERLAY_RL,
                   0x380013, new Vector3f(38f/255f, 0f, 13f/255f),
                   FluidType.Properties.create().lightLevel(14).density(-3).canConvertToSource(true).sound(SoundAction.get("drink"), SoundEvents.HONEY_DRINK))
            );


    private static RegistryObject<FluidType> soap(String name, FluidType.Properties block){


        return FLUID_TYPES.register(name, ()-> new BaseFluidType(
                WATER_STILL_RL,
                WATER_FLOWING_RL,
                ResourceLocation.fromNamespaceAndPath(VoidkinMod.MODID, "misc/in_soap_water"),
                 0xA1E038D0,
                new Vector3f(224f / 255f, 56f / 255f, 208f / 255f),
                block)
        );
    }


    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
}
