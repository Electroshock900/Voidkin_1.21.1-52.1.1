package net.voidkin.voidkin.effect;


import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidkin.voidkin.VoidkinMod;



public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, VoidkinMod.MODID);

    public static final RegistryObject<MobEffect> SPIDER_EFFECT = MOB_EFFECTS.register("spider",
            () -> new SpiderEffect(MobEffectCategory.BENEFICIAL, 0x36ebab)
                    .addAttributeModifier(Attributes.MOVEMENT_SPEED, ResourceLocation.fromNamespaceAndPath(VoidkinMod.MODID,"spider"),
                            1.2f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(Attributes.ARMOR,ResourceLocation.fromNamespaceAndPath(VoidkinMod.MODID, "lunar_speed"),
                            3.0f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
    );

    public static final RegistryObject<MobEffect> LUNAR_SPEED = MOB_EFFECTS.register("lunar_speed",
            ()-> new LunarSpeedEffect(MobEffectCategory.BENEFICIAL, 0xefe8d0));
                    /*.addAttributeModifier(Attributes.MOVEMENT_SPEED, ResourceLocation.fromNamespaceAndPath(VoidkinMod.MODID,"lunar_speed"),
                            1.7f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));*/

    public static void register(IEventBus eventBus){
        MOB_EFFECTS.register(eventBus);
    }
}
