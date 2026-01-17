package net.voidkin.voidkin.block.custom;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.voidkin.voidkin.block.blockentity.VoidPedestalBlockEntity;
import org.jetbrains.annotations.Nullable;

public class VoidPedestalBlock extends BaseEntityBlock {
    public static final VoxelShape SHAPE = Block.box(2, 0, 2, 14, 13, 14);
    public static final MapCodec<VoidPedestalBlock> CODEC = simpleCodec(VoidPedestalBlock::new);

    public VoidPedestalBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new VoidPedestalBlockEntity(blockPos, blockState);
    }

    @Override
    protected RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    /*@Override
    protected void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        if(pState.getBlock() != pNewState.getBlock()) {
            if(pLevel.getBlockEntity(pPos) instanceof VoidPedestalBlockEntity pedestalBlockEntity) {
                pedestalBlockEntity.inventory..dropContents(pLevel, pPos, pedestalBlockEntity);
                pLevel.updateNeighbourForOutputSignal(pPos, this);
            }
            super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
        }
    }*/

    @Override
    protected ItemInteractionResult useItemOn(ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos,
                                              Player pPlayer, InteractionHand pHand, BlockHitResult pHitResult) {
        if(pLevel.getBlockEntity(pPos) instanceof VoidPedestalBlockEntity pedestalBlockEntity) {
            if(pPlayer.isCrouching() && !pLevel.isClientSide()) {
                ((ServerPlayer) pPlayer).openMenu(new SimpleMenuProvider(pedestalBlockEntity, Component.literal("Pedestal")), pPos);
                return ItemInteractionResult.SUCCESS;
            }
            int freeSlot = pPlayer.getInventory().getFreeSlot();

            int matchingSlot = pPlayer.getInventory().findSlotMatchingItem(pStack);

            if (pedestalBlockEntity.inventory.getStackInSlot(0).isEmpty() && !pStack.is(Items.AIR)){
                pedestalBlockEntity.inventory.insertItem(0, pStack.copy(), false);
                pLevel.sendBlockUpdated(pedestalBlockEntity.getBlockPos(),pedestalBlockEntity.getBlockState(),pedestalBlockEntity.getBlockState(),3);
                LogUtils.getLogger().debug("String");
                pStack.shrink(1);
                //pStack.shrink(pStack.getCount());

                pLevel.playSound(pPlayer, pPos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 3f);
            }else if(!pedestalBlockEntity.inventory.getStackInSlot(0).isEmpty()){
                int fff = pedestalBlockEntity.inventory.getStackInSlot(0).getCount();
                ItemStack stackOnPedestal = pedestalBlockEntity.inventory.getStackInSlot(0);
                pedestalBlockEntity.inventory.extractItem(0, fff, false);
                pLevel.sendBlockUpdated(pedestalBlockEntity.getBlockPos(),pedestalBlockEntity.getBlockState(),pedestalBlockEntity.getBlockState(),3);
                //pPlayer.setItemInHand(InteractionHand.MAIN_HAND,stackOnPedestal);


                pPlayer.getInventory().add(matchingSlot,stackOnPedestal);
                pLevel.playSound(pPlayer, pPos, SoundEvents.ENDERMAN_AMBIENT, SoundSource.BLOCKS, 1f, 1.2f);

                LogUtils.getLogger().debug("EMPTY STRING");
                //pedestalBlockEntity.inventory.extractItem(0,1,false);
                //pLevel.playSound(pPlayer, pPos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 2f);
            }
        }

        return ItemInteractionResult.SUCCESS;
    }
}
