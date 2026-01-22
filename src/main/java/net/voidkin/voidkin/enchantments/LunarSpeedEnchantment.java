package net.voidkin.voidkin.enchantments;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.MapCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.item.enchantment.effects.EnchantmentLocationBasedEffect;
import net.minecraft.world.phys.Vec3;

import java.util.UUID;

public record LunarSpeedEnchantment() implements EnchantmentLocationBasedEffect {
    public static final MapCodec<LunarSpeedEnchantment> CODEC = MapCodec.unit(LunarSpeedEnchantment::new);
    /*public static final Codec<LunarSpeed> CODEC =
            Codec.FLOAT.fieldOf("speed_bonus")
                    .xmap(LunarSpeed::new, e -> e.speedBonus)
                    .codec();*/

    private static final UUID SPEED_UUID =
            UUID.fromString("6d3c7f6f-9b5f-4c7e-9a23-1d2c84c3a111");


    private static final ResourceLocation SPEED_ID = ResourceLocation.withDefaultNamespace("sprinting");

    private static final float speedBonus = 2.4F;

    @Override
    public void onChangedBlock(ServerLevel pLevel, int pEnchantmentLevel, EnchantedItemInUse pItem, Entity pEntity, Vec3 pPos, boolean pApplyTransientEffects) {

        if (!(pEntity instanceof LivingEntity living)) return;

        var attribute = living.getAttribute(Attributes.MOVEMENT_SPEED);
        if (attribute == null) return;

        boolean isNight = pLevel.isNight();
        var existing = attribute.getModifier(SPEED_ID);

        if (isNight) {
            if (existing == null)
            {
                attribute.addTransientModifier(new AttributeModifier(
                        SPEED_ID,
                        speedBonus * pEnchantmentLevel,
                        AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                ));
                //attribute.removeModifier(new AttributeModifier(SPEED_ID));
            }else {attribute.removeModifier(SPEED_ID);}
        }
        if (!isNight){
            if (existing == null) {
                attribute.addTransientModifier(new AttributeModifier(
                        SPEED_ID,
                        10/speedBonus,
                        AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                ));
            }else {
                attribute.removeModifier(SPEED_ID);
            }
        }
    }


    @Override
    public MapCodec<? extends EnchantmentLocationBasedEffect> codec() {
        return CODEC;
    }
}
