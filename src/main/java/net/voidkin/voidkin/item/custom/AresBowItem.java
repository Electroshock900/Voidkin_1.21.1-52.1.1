package net.voidkin.voidkin.item.custom;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.voidkin.voidkin.item.projectiles.AresArrowItem;
import net.voidkin.voidkin.item.ModItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class AresBowItem extends ProjectileWeaponItem {

    public AresBowItem(Properties properties) {
        //super(ModMaterials.CACTUS,0,0.0F,properties);
        super(properties);
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return ARROW_ONLY;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        pPlayer.startUsingItem(pHand);
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        boolean flag = !pPlayer.getProjectile(itemstack).isEmpty();

        InteractionResultHolder<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, pLevel, pPlayer, pHand, flag);
        if (ret != null) return ret;

        if (!pPlayer.getAbilities().instabuild && !flag) {
            return InteractionResultHolder.fail(itemstack);
        } else {
            pPlayer.startUsingItem(pHand);
            return InteractionResultHolder.consume(itemstack);
        }

     //   return InteractionResultHolder.consume(player.getItemInHand(hand));
    }
   @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity shooter, int pTimeLeft) {
       super.releaseUsing(stack, level, shooter, pTimeLeft);
       if (shooter instanceof Player player) {
            /*if (stack.getDamageValue() >= stack.getMaxDamage() - 1)
                return;

            // Add a cooldown so you can't fire rapidly
            player.getCooldowns().addCooldown(this, 5);

            if (!level.isClientSide) {
                AresArrow arrow = new AresArrow(level, player);

                arrow.tickCount = 35;

                arrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, 5, 1);
                arrow.setBaseDamage(13.5);
                arrow.isNoGravity();

                stack.hurtAndBreak(1, shooter, p -> p.broadcastBreakEvent(shooter.getUsedItemHand()));
                level.addFreshEntity(arrow);

                // Trigger our animation
                // We could trigger this outside of the client-side check if only wanted the animation to play for the shooter
                // But we'll fire it on the server so all nearby players can see it
                //triggerAnim(player.json, GeoItem.getOrAssignId(stack, (ServerLevel)level), "shoot_controller", "shoot");
            }
        }*/
           boolean flag = player.getAbilities().instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY.getOrThrow(player.level()), stack) > 0;
           ItemStack itemstack = player.getProjectile(stack);

           int i = this.getUseDuration(stack,player) - pTimeLeft;
           i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, level, player, i, !itemstack.isEmpty() || flag);
           if (i < 0) return;

           if (!itemstack.isEmpty() || flag) {
               if (itemstack.isEmpty()) {
                   itemstack = new ItemStack(ModItems.ARESARROW.get());
               }

               float f = getPowerForTime(i);
               if (!((double) f < 0.1D)) {
                   boolean flag1 = player.getAbilities().instabuild || (itemstack.getItem() instanceof AresArrowItem && ((AresArrowItem) itemstack.getItem()).isInfinite(itemstack, stack, player));
                   if (!level.isClientSide) {
                       AresArrowItem arrowitem = (AresArrowItem) (itemstack.getItem() instanceof AresArrowItem ? itemstack.getItem() : ModItems.ARESARROW.get());
                       AbstractArrow abstractarrow = arrowitem.createArrow(level, itemstack, player);
                       abstractarrow = customArrow(abstractarrow);
                       abstractarrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, f * 3.0F, 1.0F);
                       if (f == 1.0F) {
                           abstractarrow.setCritArrow(true);
                       }

                       int j = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER.getOrThrow(level), stack);
                       if (j > 0) {
                           abstractarrow.setBaseDamage(abstractarrow.getBaseDamage() + (double) j * 0.5D + 0.5D);
                       }

                       int k = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH.getOrThrow(level), stack);
                       if (k > 0) {
                           //abstractarrow.setKnockback(k);
                       }

                       if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAME.getOrThrow(level), stack) > 0) {
                           //abstractarrow.setSecondsOnFire(100);
                       }

                       /*stack.hurtAndBreak(1, player, (p_289501_) -> {
                           p_289501_.broadcastBreakEvent(player.getUsedItemHand());
                       });*/
                       if (flag1 || player.getAbilities().instabuild && (itemstack.is(Items.SPECTRAL_ARROW) || itemstack.is(Items.TIPPED_ARROW))) {
                           abstractarrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                       }

                       level.addFreshEntity(abstractarrow);
                   }

                   level.playSound((Player) null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                   if (!flag1 && !player.getAbilities().instabuild) {
                       itemstack.shrink(1);
                       if (itemstack.isEmpty()) {
                           player.getInventory().removeItem(itemstack);
                       }
                   }

                   player.awardStat(Stats.ITEM_USED.get(this));
               }
           }
       }
   }

       @Override
       public UseAnim getUseAnimation (ItemStack p_41452_){
           return UseAnim.BOW;
       }

       @Override
       public int getUseDuration (ItemStack p_41454_, LivingEntity pEntity){
           return 72000;//super.getUseDuration(p_41454_);
       }

       public AbstractArrow customArrow (AbstractArrow arrow){
           return arrow;
       }

       public int getDefaultProjectileRange () {
           return 15;
       }

    @Override
    protected void shootProjectile(LivingEntity pShooter, Projectile pProjectile, int pIndex, float pVelocity, float pInaccuracy, float pAngle, @Nullable LivingEntity pTarget) {

    }

    public static float getPowerForTime ( int pCharge){
           float f = (float) pCharge / 20.0F;
           f = (f * f + f * 2.0F) / 3.0F;
           if (f > 1.0F) {
               f = 1.0F;
           }

           return f;
       }
   }