package net.voidkin.voidkin.effect;

import com.ibm.icu.impl.DayPeriodRules;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.storage.loot.predicates.WeatherCheck;

public class LunarSpeedEffect extends MobEffect {
    protected LunarSpeedEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }
    protected LunarSpeedEffect(MobEffectCategory pCategory, int pColor, ParticleOptions pParticle) {
        super(pCategory, pColor, pParticle);
    }

    @Override
    public boolean applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (pLivingEntity instanceof Entity entity){

        }
        return super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {
        return super.shouldApplyEffectTickThisTick(pDuration, pAmplifier);
    }
}
