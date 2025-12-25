package net.voidkin.voidkin.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.item.enchantment.Enchantments;
import net.voidkin.voidkin.entity.projectiles.Thrown_Chakram;
import net.voidkin.voidkin.entity.ModEntities;
import net.voidkin.voidkin.item.ModItems;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ChakramItem extends SwordItem {

    public ChakramItem(Tier tier, int attack_damage, float attack_speed, Properties properties) {
        super(tier,
                properties);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        //builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", 8.0D, AttributeModifier.Operation.ADDITION));
        //builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", (double)-2.9F, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }

    @Override
    public boolean useOnRelease(ItemStack pStack) {
        return super.useOnRelease(pStack);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        pLevel.playSound((Player)null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.FIRECHARGE_USE, SoundSource.NEUTRAL, 0.5F, 0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!pLevel.isClientSide) {
            Thrown_Chakram chakram = new Thrown_Chakram(pLevel, pPlayer, itemstack);
            //chakram.setItem(itemstack);
            chakram.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1.5F, 1.0F);
            pLevel.addFreshEntity(chakram);
        }

        pPlayer.awardStat(Stats.ITEM_USED.get(this));
        if (!pPlayer.getAbilities().instabuild) {
            itemstack.shrink(1);
        }

        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
    }


    public Thrown_Chakram createArrow(Level world, ItemStack ammoStack, LivingEntity shooter) {
        return new Thrown_Chakram(ModEntities.CHAKRAM.get(), shooter, world);
    }


    //EntityType<? extends Thrown_Chakram> type;

    private final Multimap<Attribute, AttributeModifier> defaultModifiers;





    public UseAnim getUseAnimation(ItemStack p_43417_) {
        return UseAnim.SPEAR;
    }


    public int getUseDuration(ItemStack p_43419_) {
        return 72000;
    }



    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int p_43397_) {
        if (entity instanceof Player player) {
            int i = this.getUseDuration(stack) - p_43397_;
            if (i >= 10) {
                int j = (int)EnchantmentHelper.getEnchantmentLevel(Enchantments.LOYALTY.getOrThrow(level), entity);
                if (j <= 0 || player.isInWaterOrRain()) {
                    if (!level.isClientSide) {
                        /*stack.hurtAndBreak(1, player, (p_43388_) -> {
                            p_43388_.broadcastBreakEvent(entity.getUsedItemHand());
                        });*/
                        if (j == 0) {
                            Thrown_Chakram thrownChakram = new Thrown_Chakram(stack.is(ModItems.CHAKRAM.get()) ? ModEntities.CHAKRAM.get() : ModEntities.CHAKRAM.get(), level, player, stack);
                            thrownChakram.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.5F, 1.0F);
                            if (player.getAbilities().instabuild) {
                                thrownChakram.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                            }

                            level.addFreshEntity(thrownChakram);
                            //level.playSound((Player)null, thrownChakram, SoundEvents.TRIDENT_THROW, SoundSource.PLAYERS, 1.0F, 1.0F);
                            if (!player.getAbilities().instabuild) {
                                player.getInventory().removeItem(stack);
                            }
                        }
                    }

                    player.awardStat(Stats.ITEM_USED.get(this));
                    if (j > 0) {
                        float f7 = player.getYRot();
                        float f = player.getXRot();
                        float f1 = -Mth.sin(f7 * ((float)Math.PI / 180F)) * Mth.cos(f * ((float)Math.PI / 180F));
                        float f2 = -Mth.sin(f * ((float)Math.PI / 180F));
                        float f3 = Mth.cos(f7 * ((float)Math.PI / 180F)) * Mth.cos(f * ((float)Math.PI / 180F));
                        float f4 = Mth.sqrt(f1 * f1 + f2 * f2 + f3 * f3);
                        float f5 = 3.0F * ((1.0F + (float)j) / 4.0F);
                        f1 *= f5 / f4;
                        f2 *= f5 / f4;
                        f3 *= f5 / f4;
                        player.push((double)f1, (double)f2, (double)f3);
                        player.startAutoSpinAttack(20,2.0f,new ItemStack(stack.getItem()));
                        //if (player.isOnGround()) {
                        float f6 = 1.1999999F;
                        player.move(MoverType.SELF, new Vec3(0.0D, (double)1.1999999F, 0.0D));
                        //}

                        SoundEvent soundevent;
                        if (j >= 3) {
                            soundevent = SoundEvents.FIRECHARGE_USE;
                        } else if (j == 2) {
                            soundevent = SoundEvents.FIRE_EXTINGUISH;
                        } else {
                            soundevent = SoundEvents.DRAGON_FIREBALL_EXPLODE;
                        }

                        level.playSound((Player)null, player, soundevent, SoundSource.PLAYERS, 1.0F, 1.0F);
                    }

                }
            }
        }
    }





}


