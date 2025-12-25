package net.voidkin.voidkin.recipe;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
//import net.voidkin.voidkin.block.custom.c.DomRecipe;
import net.voidkin.voidkin.recipe.recipeinput.AltarRecipeInput;


public record AltarRecipe2(NonNullList<Ingredient> ingredients, ItemStack output) implements Recipe<AltarRecipeInput>/*, Container*/ {

    @Override
    public boolean matches(AltarRecipeInput pContainer, Level pLevel) {
        if(pLevel.isClientSide()) {
            return false;
        }
        boolean matches = false;
        matches = ingredients.get(0).test(pContainer.mainPedestalItem());

        if(!ingredients.get(0).test(pContainer.mainPedestalItem())){
            return false;
        }
        for (int i=1; i<ingredients.size(); i++){
             //ItemStack stack : pContainer.sidePedestalItems()
            if(!ingredients.get(i).test(pContainer.sidepedestals().get(i-1))){
                return false;
            }

        }


        return matches;
                //ingredients.get(0).test(pContainer.getItem(0));
    }

    @Override
    public ItemStack assemble(AltarRecipeInput pInput, HolderLookup.Provider pRegistries) {
        return output.copy();
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return ingredients;
    }

    public ItemStack getItem(int i){
        if (i==0){
            return ingredients.get(0).getItems()[0];
        }
        return ingredients.get(i).getItems()[0];
    }


    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider pRegistryAccess) {
        return output.copy();
    }


    @Override
    public RecipeSerializer<?> getSerializer() {
        return null;//ModRecipes.ALTAR_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return null;//ModRecipes.ALTAR_TYPE.get();
    }

    /*@Override
    public void clearContent() {

    }*/

    public static class Type implements RecipeType<AltarRecipe2> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "altar_crafting";
    }

    public static class Serializer implements RecipeSerializer<AltarRecipe2> {
        public static final AltarRecipe2.Serializer INSTANCE = new AltarRecipe2.Serializer();
        public static final MapCodec<AltarRecipe2> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(

                        Ingredient.CODEC_NONEMPTY
                                .listOf()
                                .fieldOf("ingredients")
                                .flatXmap(
                                        list -> {
                                            Ingredient[] aingredient = list.stream().filter(thing1 -> !thing1.isEmpty()).toArray(Ingredient[]::new);
                                            return DataResult.success(NonNullList.of(Ingredient.EMPTY, aingredient));
                                        },
                                        DataResult::success
                                ).forGetter(AltarRecipe2::getIngredients),
                ItemStack.CODEC.fieldOf("result").forGetter(AltarRecipe2::output)
        ).apply(inst, AltarRecipe2::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, AltarRecipe2> STREAM_CODEC =
                StreamCodec.of(AltarRecipe2.Serializer::toNetwork, AltarRecipe2.Serializer::fromNetwork);

        @Override
        public MapCodec<AltarRecipe2> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, AltarRecipe2> streamCodec() {
            return STREAM_CODEC;
        }
        private static AltarRecipe2 fromNetwork(RegistryFriendlyByteBuf buffer) {
            /*
            String s = buffer.readUtf();
            CraftingBookCategory craftingbookcategory = buffer.readEnum(CraftingBookCategory.class);
            */
            int i = buffer.readVarInt();
            NonNullList<Ingredient> nonnulllist = NonNullList.withSize(i, Ingredient.EMPTY);
            nonnulllist.replaceAll(p_327214_ -> Ingredient.CONTENTS_STREAM_CODEC.decode(buffer));
            ItemStack itemstack = ItemStack.STREAM_CODEC.decode(buffer);
            return new AltarRecipe2(nonnulllist, itemstack);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, AltarRecipe2 recipe) {/*
            buffer.writeUtf(recipe.group);
            buffer.writeEnum(recipe.category);*/
            buffer.writeVarInt(recipe.ingredients.size());

            for (Ingredient ingredient : recipe.ingredients) {
                Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, ingredient);
            }

            ItemStack.STREAM_CODEC.encode(buffer, recipe.output);
        }
    }
}


