package net.voidkin.voidkin.block.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.block.Block;

public class VoidPedestalBlock extends Block {
    public VoidPedestalBlock(Properties pProperties) {
        super(pProperties);
    }
    /**public FallingBlockEntity create_FallingBlock(BlockPos cursor){
        this.fallingblock = FallingBlockEntity.fall(warudo, cursor, this.getBlockState() );
        this.fallingblock.setNoGravity(true);
        warudo.addFreshEntity(this.fallingblock);
        return this.fallingblock;
    }**/

    //Minecraft.getInstance().getBlockRenderer().renderSingleBlock(blockstate, posStack, bufferSource, pakcedLight, packedOverlay, modelData, renderType);
}
