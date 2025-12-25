package net.voidkin.voidkin.recipe;

import net.minecraft.world.item.crafting.RecipeType;
import net.voidkin.voidkin.VoidkinMod;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, VoidkinMod.MODID);
    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, VoidkinMod.MODID);
/*
    public static final RegistryObject<RecipeSerializer<CandyCaneFurnaceRecipe>> CANDY_CANE_FURNACE_SERIALIZER =
            SERIALIZERS.register("candy_cane_furnace", () -> CandyCaneFurnaceRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeType<CandyCaneFurnaceRecipe>> CANDY_CANE_FURNACE_TYPE =
            TYPES.register("candy_cane_furnace", () -> new RecipeType<AltarRecipe>() {
                public String toString() { return "voidkin:candy_cane_furnace"; }
            });*/
    public static final RegistryObject<RecipeSerializer<PolisherRecipe>> POLISHER_SERIALIZER =
            SERIALIZERS.register("polisher", PolisherRecipe.Serializer::new);
    public static final RegistryObject<RecipeType<PolisherRecipe>> POLISHER_TYPE =
            TYPES.register("polisher", () -> new RecipeType<PolisherRecipe>() {
                public String toString() { return "polisher"; }
            });

    public static final RegistryObject<RecipeSerializer<AltarRecipe>> ALTAR_SERIALIZER =
            SERIALIZERS.register("altar_crafting", AltarRecipe.Serializer::new);
    public static final RegistryObject<RecipeType<AltarRecipe>> ALTAR_TYPE =
            TYPES.register("altar_crafting", () -> new RecipeType<AltarRecipe>() {
                public String toString() { return "altar_crafting"; }
            });

    public static final RegistryObject<RecipeSerializer<CrystallizerRecipe>> CRYSTALLIZER_SERIALIZER =
            SERIALIZERS.register("crystallizing", CrystallizerRecipe.Serializer::new);
    public static final RegistryObject<RecipeType<CrystallizerRecipe>> CRYSTALLIZER_TYPE =
            TYPES.register("crystallizing", () -> new RecipeType<CrystallizerRecipe>() {
                @Override
                public String toString() {
                    return "crystallizing";
                }
            });


    /**public static final RegistryObject<RecipeType<MultiblockRecipe>> MULTIBLOCK_TYPE =
            TYPES.register("multiblock", () -> new RecipeType<>() {
                public String toString() { return "voidkin:multiblock"; }
            });**/

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
    }
}
