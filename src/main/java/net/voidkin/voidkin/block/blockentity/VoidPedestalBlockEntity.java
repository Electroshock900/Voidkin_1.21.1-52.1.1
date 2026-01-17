package net.voidkin.voidkin.block.blockentity;

import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.voidkin.voidkin.block.ModBlockEntities;
import net.voidkin.voidkin.menu.PedestalMenu;
import net.voidkin.voidkin.menu.VoidPedestalMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class VoidPedestalBlockEntity extends BlockEntity implements MenuProvider {
    public final ItemStackHandler inventory = new ItemStackHandler(1) {
        @Override
        protected int getStackLimit(int slot, @NotNull ItemStack stack) {
            return 1;
        }

        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(level !=null && !level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
                LogUtils.getLogger().debug("Pedestal Item: " + inventory.getStackInSlot(0));
            }
        }
    };
    protected final ContainerData data;
    private int progress;
    private float rotation = 0;
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    public VoidPedestalBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.VOID_PEDESTAL.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return progress;



            }

            @Override
            public void set(int pIndex, int pValue) {
                if (pIndex == 0) {
                    progress = pValue;
                    //case 1 -> maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 1;
            }
        };
    }

    public void sync() {
        if (level != null && !level.isClientSide) {
            setChanged();
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        pTag.put("pedestal_inventory", inventory.serializeNBT(pRegistries));
        super.saveAdditional(pTag, pRegistries);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        ItemStackHandler newInv = new ItemStackHandler(1) {
            @Override
            protected int getStackLimit(int slot, @NotNull ItemStack stack) {
                return 1;
            }

            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }
        };

        newInv.deserializeNBT(pRegistries, pTag.getCompound("pedestal_inventory"));
        this.inventory.setStackInSlot(0, newInv.getStackInSlot(0));
        //inventory.deserializeNBT(pRegistries, pTag.getCompound("pedestal_inventory"));
    }

    public float getRenderingRotation() {
        rotation += 0.5f;
        if(rotation >= 360) {
            rotation = 0;
        }
        return rotation;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        //return saveWithoutMetadata(pRegistries);
        CompoundTag tag = super.getUpdateTag(pRegistries);
        tag.put("pedestal_inventory", inventory.serializeNBT(pRegistries));
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider registries) {
        if (tag.contains("pedestal_inventory")) {
            inventory.deserializeNBT(registries, tag.getCompound("pedestal_inventory"));
        }
    }


    @Override
    public void onDataPacket(Connection connection,
                             ClientboundBlockEntityDataPacket packet,
                             HolderLookup.Provider lookup) {
        if (packet.getTag() != null) {
            loadAdditional(packet.getTag(), lookup);
        }
    }


    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> inventory);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            if(side == null) {
                return lazyItemHandler.cast();
            }
        }

        return super.getCapability(cap, side);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.voidkin.void_pedestal");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new VoidPedestalMenu(i, inventory, this, this.data);
    }
}