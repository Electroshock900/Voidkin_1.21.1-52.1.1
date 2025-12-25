package net.voidkin.voidkin.util;

import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

public class ModDamageSources {

    private final Registry<DamageType> DAMAGE_TYPES;
    public static DamageSource LIFE_STEAL;
    public static DamageSource CHAKRAM;
    public ModDamageSources(RegistryAccess registryAccess, Registry<DamageType> damageTypes){
        this.DAMAGE_TYPES=registryAccess.registryOrThrow(Registries.DAMAGE_TYPE);
        LIFE_STEAL= this.source(ModDamageTypes.LIFE_STEALS);
        CHAKRAM= this.source(ModDamageTypes.CHAKRAMS);
    }

    private DamageSource source(ResourceKey<DamageType> pDamageTypeKey) {
        return new DamageSource(this.DAMAGE_TYPES.getHolderOrThrow(pDamageTypeKey));
    }

    private DamageSource source(ResourceKey<DamageType> pDamageTypeKey, @Nullable Entity pEntity) {
        return new DamageSource(this.DAMAGE_TYPES.getHolderOrThrow(pDamageTypeKey), pEntity);
    }

    private DamageSource source(ResourceKey<DamageType> pDamageTypeKey, @Nullable Entity pCausingEntity, @Nullable Entity pDirectEntity) {
        return new DamageSource(this.DAMAGE_TYPES.getHolderOrThrow(pDamageTypeKey), pCausingEntity, pDirectEntity);
    }


/**
    public ModDamageSources(Holder<DamageType> pType, @Nullable Entity pDirectEntity, @Nullable Entity pCausingEntity, Registry<DamageTypes> damageTypes) {
        super(pType, pDirectEntity, pCausingEntity);
        DAMAGE_TYPES = damageTypes;
    }**/
}
