package net.voidkin.voidkin.block.blockentity;

import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.voidkin.voidkin.block.ModBlockEntities;
import net.voidkin.voidkin.menu.AltarMenu;
import net.voidkin.voidkin.particles.ModParticles;
import net.voidkin.voidkin.recipe.AltarRecipe;
import net.voidkin.voidkin.recipe.ModRecipes;
import net.voidkin.voidkin.recipe.recipeinput.AltarRecipeInput;
import net.voidkin.voidkin.block.ModBlocks;
import net.voidkin.voidkin.item.ModItems;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector2i;

import java.util.List;
import java.util.Optional;

public class VoidAltarBlockEntity extends BlockEntity implements MenuProvider {
    public static List<Vector2i> offsets = List.of(
            new Vector2i(-2,-2),
            new Vector2i(0,-3),
            new Vector2i(2,-2),
            new Vector2i(-3,0),
            //new Vector2i(0,0),
            new Vector2i(3,0),
            new Vector2i(-2,2),
            new Vector2i(0,3),
            new Vector2i(2,2));

    public final ItemStackHandler itemHandler = new ItemStackHandler(9) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 78;

    public VoidAltarBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.ALTAR_PEDESTAL.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> VoidAltarBlockEntity.this.progress;
                    case 1 -> VoidAltarBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> VoidAltarBlockEntity.this.progress = pValue;
                    case 1 -> VoidAltarBlockEntity.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    public ItemStack getRenderStack() {
        //if(itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty()) {
            return itemHandler.getStackInSlot(INPUT_SLOT);
        //} else {*/
           // return itemHandler.getStackInSlot(OUTPUT_SLOT);
        //}
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.voidkin.altar_pedestal");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new AltarMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        pTag.put("altar_inventory", itemHandler.serializeNBT(pRegistries));
        pTag.putInt("altar_main.progress", progress);
        pTag.putInt("altar_main.max_progress", maxProgress);

        super.saveAdditional(pTag, pRegistries);
    }

    @Override
    public void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        itemHandler.deserializeNBT(pRegistries,pTag.getCompound("altar_inventory"));
        progress = pTag.getInt("altar_main.progress");
        maxProgress = pTag.getInt("altar_main.max_progress");
    }
/*
    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {

        Optional<RecipeHolder<AltarRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) return;
        if (!hasPedestals()){
            return;
        }
        if(!pedestalsItems()){
            return;
        }
       // if(!level.isClientSide()){
       // if (hasPedestals()) {
            if(!level.isClientSide()) {

            }
            if (!hasRecipe()) {
                return;
            }

                //pLevel.addParticle(ParticleTypes.LAVA, Mth.sin((float) pPos.getX()), pPos.getY()+1, Mth.sin((float) pPos.getX() + 0.5F), 0, 0, 0);

            if(!mainItem()) {
                return;
            }
        //}
        if(hasPedestals()) {
            if(hasRecipe()){
            level.addParticle(ModParticles.VOID_FLAME.get(),
                    this.getBlockPos().getX() + 0.5,
                    this.getBlockPos().getY() + 1,
                    this.getBlockPos().getZ() + 0.5,
                    0,
                    0,
                    0

            );
            if (mainItem() && pedestalsItems()){
                setChanged(pLevel,pPos,pState);
                removefromSides();
                exchangeItemInMainPedestal();
            }
            }
        }
            //if (hasPedestals()) {
               // if(mainItem()){
                    //removefromSides();
                    //exchangeItemInMainPedestal();
                //if (hasRecipe()) {}
                   /* increaseCraftingProgress();
                    setChanged(pLevel, pPos, pState);
                    removefromSides();
                    exchangeItemInMainPedestal();
                    //LogUtils

                    if (hasProgressFinished()) {
                        craftItem();
                        resetProgress();
                    }
                } else {
                    resetProgress();
                }
                //}
            //}
        //}


    }*/

    public void tick(Level level, BlockPos pos, BlockState state) {
        if (level.isClientSide) return;

        // 1. Must have all pedestal blocks
        if (!hasPedestals()) return;

        // 2. Resolve recipe ONCE
        Optional<RecipeHolder<AltarRecipe>> recipeOpt = getCurrentRecipe();
        if (recipeOpt.isEmpty()) return;

        AltarRecipe recipe = recipeOpt.get().value();

        // 3. Main altar item must match
        if (!recipe.getIngredients().getFirst().test(itemHandler.getStackInSlot(0))) {
            resetProgress();
            return;
        }

        // 4. Pedestal items must match
        if (!pedestalsMatchRecipe(recipe)) {
            resetProgress();
            return;
        }

        // 5. Progress crafting
        increaseCraftingProgress();

        setChanged(level, pos, state);

        if (hasProgressFinished()) {
            craft(recipe);
            resetProgress();
        }
        if((level.isClientSide() && hasPedestals())){
            spawnParticleRing();
        }
    }

    private void resetProgress() {
        progress = 0;
    }


    private boolean hasRecipe() {
        Optional<RecipeHolder<AltarRecipe>> recipe = getCurrentRecipe();
        Level level = this.getLevel();
        ItemStack result;
        if (hasPedestals()) {
            if (recipe.isEmpty()) {
                return false;
            }
            result = recipe.get().value().output();
        }else{return false;}

    result = recipe.get().value().output();
    return canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private Optional<RecipeHolder<AltarRecipe>> g2etCurrentRecipe() {
        return this.level.getRecipeManager()
                .getRecipeFor(ModRecipes.ALTAR_TYPE.get(), new AltarRecipeInput(
                itemHandler.getStackInSlot(0),
                offsets.stream().map(offset -> {
                    if(hasPedestals()){
                        return((VoidPedestalBlockEntity) level.getBlockEntity(this.getBlockPos().offset(offset.x, 0, offset.y)))
                            .inventory.getStackInSlot(0);
                }else{
                    return null;}
                }).toList()
        ), level);
    }
    private Optional<RecipeHolder<AltarRecipe>> getCurrentRecipe() {
        if (level == null) return Optional.empty();

        return level.getRecipeManager().getRecipeFor(
                ModRecipes.ALTAR_TYPE.get(),
                new AltarRecipeInput(
                        itemHandler.getStackInSlot(0),
                        offsets.stream()
                                .map(offset -> {
                                    BlockEntity be = level.getBlockEntity(worldPosition.offset(offset.x, 0, offset.y));
                                    if (be instanceof VoidPedestalBlockEntity pedestal) {
                                        return pedestal.inventory.getStackInSlot(0);
                                    }
                                    return ItemStack.EMPTY;
                                })
                                .toList()
                ),
                level
        );
    }
    private boolean pedestalsMatchRecipe(AltarRecipe recipe) {
        NonNullList<Ingredient> ingredients = recipe.getIngredients();

        // Ingredient[0] = main altar item
        int pedestalIngredientCount = ingredients.size() - 1;

        if (pedestalIngredientCount != offsets.size()) {
            return false;
        }

        for (int i = 0; i < offsets.size(); i++) {
            Vector2i offset = offsets.get(i);
            Ingredient ingredient = ingredients.get(i + 1);

            BlockEntity be = level.getBlockEntity(worldPosition.offset(offset.x, 0, offset.y));
            if (!(be instanceof VoidPedestalBlockEntity pedestal)) {
                return false;
            }

            ItemStack stack = pedestal.inventory.getStackInSlot(0);
            if (!ingredient.test(stack)) {
                return false;
            }
        }

        return true;
    }
    private void craft(AltarRecipe recipe) {
        // Remove main item
        this.itemHandler.extractItem(0, 1, false);

        // Clear pedestal items
        for (Vector2i offset : offsets) {
            BlockEntity be = level.getBlockEntity(worldPosition.offset(offset.x, 0, offset.y));
            if (be instanceof VoidPedestalBlockEntity pedestal) {
                pedestal.inventory.setStackInSlot(0, ItemStack.EMPTY);
                pedestal.setChanged();
            }
        }

        // Insert result
        ItemStack result = recipe.output().copy();
        this.itemHandler.insertItem(0, result, false);
    }


    private void exchangeItemInMainPedestal(){
        Optional<RecipeHolder<AltarRecipe>> recipe = getCurrentRecipe();
        this.itemHandler.extractItem(0, 64, false);
        recipe.ifPresent(altarRecipeRecipeHolder -> this.itemHandler.insertItem(0, altarRecipeRecipeHolder.value().output(), false));
    }
    private void removefromSides(){
        offsets.forEach(offset -> ((VoidPedestalBlockEntity) level.getBlockEntity(this.getBlockPos().offset(offset.x, 0, offset.y)))
                .inventory.setStackInSlot(0, ItemStack.EMPTY));

    }
    /*private boolean mainItem(){
        Optional<RecipeHolder<AltarRecipe>> recipe = getCurrentRecipe();

        NonNullList<Ingredient> ing = recipe.get().value().getIngredients();

        ItemStack itemStack = ing.getFirst().getItems()[0];
        return this.itemHandler.getStackInSlot(0).is(itemStack.getItem());
    }*/
    /*
    private boolean hasItemInPedestal(Vector2i pPos, ItemLike pItem){
        Optional<RecipeHolder<AltarRecipe>> recipe = getCurrentRecipe();
        if(recipe.isEmpty()){
            return false;
        }
        NonNullList<Ingredient> ing = recipe.get().value().getIngredients();
        int i = 1;
        for (i = 0; i < ing.size(); i++) {
            //    itemStack = new ItemStack((ItemLike) ing.get(i));
            ItemStack itemStack = recipe.get().value().getItem(i);
        }
        ItemStack itemStack = recipe.get().value().getItem(i);
        offsets.stream().allMatch(offset -> ((VoidPedestalBlockEntity) level.getBlockEntity(this.getBlockPos().offset(offset.x, 0, offset.y))).inventory.getStackInSlot(0)
                .is(ing.get(1).test(itemStack)));
        return ing.get()

    }*/
    /*private boolean pedestalsItems() {
        Optional<RecipeHolder<AltarRecipe>> recipe = getCurrentRecipe();

        NonNullList<Ingredient> ing;
        //assert recipe.isPresent();
          //  ing = recipe.get().value().getIngredients();

        ItemStack itemStack = ItemStack.EMPTY;
        //new ItemStack((ItemLike) ing.get(1));
        if(hasRecipe() && recipe.isPresent()) {
            ing = recipe.get().value().getIngredients();
            int i = 1;
            for (i = 1; i < ing.size(); i++) {
                itemStack = new ItemStack((ItemLike) ing.get(i));
            }
        }
        ItemLike finalItemStack = itemStack.getItem();
        return offsets.stream().allMatch(offset -> hasItemInPedestal(offset, finalItemStack));

    }*/
    private boolean pedestalsItems() {
        Optional<RecipeHolder<AltarRecipe>> recipe = getCurrentRecipe();
        List<Ingredient> ingredients = recipe.get().value().getIngredients();

        // ingredients[0] is main altar item
        if (ingredients.size() - 1 != offsets.size()) return false;

        for (int i = 1; i < ingredients.size(); i++) {
            Vector2i offset = offsets.get(i - 1);
            BlockEntity be = level.getBlockEntity(worldPosition.offset(offset.x, 0, offset.y));

            if (!(be instanceof VoidPedestalBlockEntity pedestal)) return false;

            ItemStack stack = pedestal.inventory.getStackInSlot(0);
            if (!ingredients.get(i).test(stack)) return false;
        }

        return true;
    }

    public boolean hasPedestals(){

        return offsets.stream().allMatch(offset ->
                level.getBlockState(worldPosition.offset(offset.x, 0, offset.y))
                        .is(ModBlocks.SIDE_ALTAR_PEDESTAL.get())
        );
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count <= this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }

    private boolean hasProgressFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftingProgress() {
        progress++;

    }
    private void s(){}


    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    private void spawnParticleRing() {
        if (level == null) return;

        double centerX = worldPosition.getX() + 0.5;
        double centerY = worldPosition.getY() + 1.1;
        double centerZ = worldPosition.getZ() + 0.5;

        // Progress-based animation
        float progressRatio = (float) progress / maxProgress;
        float radius = 1.5f + Mth.sin(progressRatio * Mth.PI) * 0.4f;

        // Rotation over time
        float time = (level.getGameTime() + level.getRandom().nextFloat()) * 0.1f;

        int particleCount = 12;

        for (int i = 0; i < particleCount; i++) {
            double angle = time + (Math.PI * 2 * i / particleCount);

            double x = centerX + Math.cos(angle) * radius;
            double z = centerZ + Math.sin(angle) * radius;

            level.addParticle(
                    ModParticles.VOID_FLAME.get(), // or ParticleTypes.SOUL_FIRE_FLAME
                    x,
                    centerY,
                    z,
                    0,
                    0.01,
                    0
            );
        }
    }

}
