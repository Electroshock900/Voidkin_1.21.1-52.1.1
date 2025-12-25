package net.voidkin.voidkin.datagen;

import net.voidkin.voidkin.VoidkinMod;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidkin.voidkin.block.ModBlocks;

@Mod.EventBusSubscriber(modid= VoidkinMod.MODID,bus=Mod.EventBusSubscriber.Bus.MOD)
public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, VoidkinMod.MODID, exFileHelper);
    }
    @SubscribeEvent
    @Override
    protected void registerStatesAndModels() {
        /*horizontalBlock(ModBlocks.CRYSTALLIZER.get(), models().orientable("voidkin:crystallizer",
                mcLoc("block/blast_furnace_side"),
                modLoc("block/crystallizer_front"),
                mcLoc("block/blast_furnace_top")));*/
        blockWithItem(ModBlocks.BLEEDING_DEITY_BLOCK);

        blockItem(ModBlocks.CRYSTALLIZER);
        /**blockWithItem(ModBlocks.DEATH_BLOCK);
        blockWithItem(ModBlocks.DEATH_PORTAL);**/
//ORES
        /**blockWithItem(ModBlocks.DARK_SHARD_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_DARK_SHARD_ORE);
        blockWithItem(ModBlocks.NETHER_DARK_SHARD_ORE);
        blockWithItem(ModBlocks.DARKNESS_BLOCK);
        blockWithItem(ModBlocks.RAW_DARKNESS_BLOCK);**/
//FOOD BLOCKS
        /**blockWithItem(ModBlocks.BBR);
        blockWithItem(ModBlocks.BBC);
        blockWithItem(ModBlocks.BCR);
        blockWithItem(ModBlocks.BCC);
        blockWithItem(ModBlocks.BMR);
        blockWithItem(ModBlocks.BMC);
        blockWithItem(ModBlocks.BPR);
        blockWithItem(ModBlocks.BPC);**/
//DARK BIOME STUFF
        //blockWithItem(ModBlocks.DARK_GRASS);
       /** blockWithItem(ModBlocks.DARK_DIRT);
        blockWithItem(ModBlocks.DARK_STONE);
        blockWithItem(ModBlocks.DARK_COBBLESTONE);
        blockWithItem(ModBlocks.BLOOD_STONE);
        blockWithItem(ModBlocks.BLOOD_COBBLESTONE);

        blockWithItem(ModBlocks.SOUND_BLOCK);
        blockWithItem(ModBlocks.DARK_PLANKS);
        blockWithItem(ModBlocks.BLOOD_PLANKS);**/
       //DARK WOOD STUFF
    /**
        stairsBlock(((StairBlock) ModBlocks.DARK_STAIRS.get()), blockTexture(ModBlocks.DARK_PLANKS.get()));
        slabBlock(((SlabBlock) ModBlocks.DARK_SLAB.get()), blockTexture(ModBlocks.DARK_PLANKS.get()), blockTexture(ModBlocks.DARK_PLANKS.get()));

        buttonBlock(((ButtonBlock) ModBlocks.DARK_BUTTON.get()), blockTexture(ModBlocks.DARK_PLANKS.get()));
        pressurePlateBlock(((PressurePlateBlock) ModBlocks.DARK_PRESSURE_PLATE.get()), blockTexture(ModBlocks.DARK_PLANKS.get()));

        fenceBlock(((FenceBlock) ModBlocks.DARK_FENCE.get()), blockTexture(ModBlocks.DARK_PLANKS.get()));
        fenceGateBlock(((FenceGateBlock) ModBlocks.DARK_FENCE_GATE.get()), blockTexture(ModBlocks.DARK_PLANKS.get()));
        wallBlock(((WallBlock) ModBlocks.DARK_WALL.get()), blockTexture(ModBlocks.DARK_PLANKS.get()));

        doorBlockWithRenderType(((DoorBlock) ModBlocks.DARK_DOOR.get()), modLoc("block/dark_door_bottom"), modLoc("block/dark_door_top"), "cutout");
        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBlocks.DARK_TRAPDOOR.get()), modLoc("block/dark_trapdoor"), true, "cutout");
**/
//BLOOD WOOD STUFF
        /**
        stairsBlock(((StairBlock) ModBlocks.BLOOD_STAIRS.get()), blockTexture(ModBlocks.BLOOD_PLANKS.get()));
        slabBlock(((SlabBlock) ModBlocks.BLOOD_SLAB.get()), blockTexture(ModBlocks.BLOOD_PLANKS.get()), blockTexture(ModBlocks.BLOOD_PLANKS.get()));

        buttonBlock(((ButtonBlock) ModBlocks.BLOOD_BUTTON.get()), blockTexture(ModBlocks.BLOOD_PLANKS.get()));
        pressurePlateBlock(((PressurePlateBlock) ModBlocks.BLOOD_PRESSURE_PLATE.get()), blockTexture(ModBlocks.BLOOD_PLANKS.get()));

        fenceBlock(((FenceBlock) ModBlocks.BLOOD_FENCE.get()), blockTexture(ModBlocks.BLOOD_PLANKS.get()));
        fenceGateBlock(((FenceGateBlock) ModBlocks.BLOOD_FENCE_GATE.get()), blockTexture(ModBlocks.BLOOD_PLANKS.get()));
        wallBlock(((WallBlock) ModBlocks.BLOOD_WALL.get()), blockTexture(ModBlocks.BLOOD_PLANKS.get()));

        doorBlockWithRenderType(((DoorBlock) ModBlocks.BLOOD_DOOR.get()), modLoc("block/blood_door_bottom"), modLoc("block/blood_door_top"), "cutout");
        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBlocks.BLOOD_TRAPDOOR.get()), modLoc("block/blood_trapdoor"), true, "cutout");
**/



 //makeStrawberryCrop((CropBlock) ModBlocks.STRAWBERRY_CROP.get(), "strawberry_stage", "strawberry_stage");
 //makeCornCrop(((CropBlock) ModBlocks.CORN_CROP.get()), "corn_stage_", "corn_stage_");


/**
    simpleBlockWithItem(ModBlocks.CATMINT.get(), models().cross(blockTexture(ModBlocks.CATMINT.get()).getPath(),
            blockTexture(ModBlocks.CATMINT.get())).renderType("cutout"));
    simpleBlockWithItem(ModBlocks.POTTED_CATMINT.get(), models().singleTexture("potted_catmint", ResourceLocation.parse("flower_pot_cross"), "plant",
            blockTexture(ModBlocks.CATMINT.get())).renderType("cutout"));

    simpleBlockWithItem(ModBlocks.LOTUS.get(), models().cross(blockTexture(ModBlocks.LOTUS.get()).getPath(),
            blockTexture(ModBlocks.LOTUS.get())).renderType("cutout"));
    simpleBlockWithItem(ModBlocks.POTTED_LOTUS.get(), models().singleTexture("potted_lotus", ResourceLocation.parse("flower_pot_cross"), "plant",
            blockTexture(ModBlocks.LOTUS.get())).renderType("cutout"));
**/


    //simpleBlockWithItem(ModBlocks.POLISHER.get(), new ModelFile.UncheckedModelFile(modLoc("block/polisher")));
    //simpleBlockWithItem(ModBlocks.VOID_ALTAR.get(), new ModelFile.UncheckedModelFile(modLoc("block/void_altar")));
//    simpleBlock(ModBlocks.AB.get(), new ModelFile.UncheckedModelFile(modLoc("block/void_altar")));
    //blockItem(ModBlocks.BLOOD_CONGEALED);
    //blockItem(ModBlocks.BLOOD_COAGULATED);

/**
        //DARK WOOD
        saplingBlock(ModBlocks.DARK_SAPLING);
        blockItem(ModBlocks.DARK_LOG);
        blockItem(ModBlocks.DARK_WOOD);
        blockItem(ModBlocks.STRIPPED_DARK_LOG);
        blockItem(ModBlocks.STRIPPED_DARK_WOOD);
        leavesBlock(ModBlocks.DARK_LEAVES);
        logBlock(((RotatedPillarBlock) ModBlocks.DARK_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.DARK_WOOD.get()), blockTexture(ModBlocks.DARK_LOG.get()), blockTexture(ModBlocks.DARK_LOG.get()));

        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_DARK_LOG.get()), blockTexture(ModBlocks.STRIPPED_DARK_LOG.get()),
                ResourceLocation.fromNamespaceAndPath(VoidkinMod.MODID, "block/stripped_dark_log_top"));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_DARK_WOOD.get()), blockTexture(ModBlocks.STRIPPED_DARK_LOG.get()),
                blockTexture(ModBlocks.STRIPPED_DARK_LOG.get()));
        signBlock((StandingSignBlock) ModBlocks.DARK_SIGN.get(), (WallSignBlock) ModBlocks.DARK_WALL_SIGN.get(),
                blockTexture(ModBlocks.DARK_PLANKS.get()));
        hangingSignBlock(ModBlocks.DARK_HANGING_SIGN.get(), ModBlocks.DARK_WALL_HANGING_SIGN.get(), blockTexture(ModBlocks.DARK_PLANKS.get()));

        //BLOOD WOOD
        saplingBlock(ModBlocks.BLOOD_SAPLING);
        blockItem(ModBlocks.BLOOD_LOG);
        blockItem(ModBlocks.BLOOD_WOOD);
        blockItem(ModBlocks.STRIPPED_BLOOD_LOG);
        blockItem(ModBlocks.STRIPPED_BLOOD_WOOD);
        leavesBlock(ModBlocks.BLOOD_LEAVES);
        logBlock(((RotatedPillarBlock) ModBlocks.BLOOD_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.BLOOD_WOOD.get()), blockTexture(ModBlocks.BLOOD_LOG.get()), blockTexture(ModBlocks.BLOOD_LOG.get()));

        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_BLOOD_LOG.get()), blockTexture(ModBlocks.STRIPPED_BLOOD_LOG.get()),
                ResourceLocation.fromNamespaceAndPath(VoidkinMod.MODID, "block/stripped_blood_log_top"));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_BLOOD_WOOD.get()), blockTexture(ModBlocks.STRIPPED_BLOOD_LOG.get()),
                blockTexture(ModBlocks.STRIPPED_BLOOD_LOG.get()));
        signBlock(((StandingSignBlock) ModBlocks.BLOOD_SIGN.get()), ((WallSignBlock) ModBlocks.BLOOD_WALL_SIGN.get()),
                blockTexture(ModBlocks.BLOOD_PLANKS.get()));
        hangingSignBlock(ModBlocks.BLOOD_HANGING_SIGN.get(), ModBlocks.BLOOD_WALL_HANGING_SIGN.get(), blockTexture(ModBlocks.BLOOD_PLANKS.get()));
**/
    }



    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ResourceLocation texture) {
        ModelFile sign = models().sign(name(signBlock), texture);
        hangingSignBlock(signBlock, wallSignBlock, sign);
    }

    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ModelFile sign) {
        simpleBlock(signBlock, sign);
        simpleBlock(wallSignBlock, sign);
    }
    private String name(Block block) {
        return key(block).getPath();
    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }
    private void leavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), ResourceLocation.withDefaultNamespace("block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(VoidkinMod.MODID +
                ":block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }
    /**
     public void makeStrawberryCrop(CropBlock block, String modelName, String textureName) {
     Function<BlockState, ConfiguredModel[]> function = state -> strawberryStates(state, block, modelName, textureName);

     getVariantBuilder(block).forAllStates(function);
     }

     private ConfiguredModel[] strawberryStates(BlockState state, CropBlock block, String modelName, String textureName) {
     ConfiguredModel[] models = new ConfiguredModel[1];
     models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((StrawberryCropBlock) block).getAgeProperty()),
     ResourceLocation.fromNamespaceAndPath(DeathMod.MODID, "block/" + textureName + state.getValue(((StrawberryCropBlock) block).getAgeProperty()))).renderType("cutout"));

     return models;
     }

     public void makeCornCrop(CropBlock block, String modelName, String textureName) {
     Function<BlockState, ConfiguredModel[]> function = state -> cornStates(state, block, modelName, textureName);

     getVariantBuilder(block).forAllStates(function);
     }

     private ConfiguredModel[] cornStates(BlockState state, CropBlock block, String modelName, String textureName) {
     ConfiguredModel[] models = new ConfiguredModel[1];
     models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((CornCropBlock) block).getAgeProperty()),
     ResourceLocation.fromNamespaceAndPath(DeathMod.MODID, "block/" + textureName + state.getValue(((CornCropBlock) block).getAgeProperty()))).renderType("cutout"));

     return models;
     }**/

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
    private void saplingBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }


}
