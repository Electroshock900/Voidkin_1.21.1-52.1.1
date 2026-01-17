package net.voidkin.voidkin.block.blockentity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.model.data.ModelData;
import net.voidkin.voidkin.block.blockentity.VoidAltarBlockEntity;
import net.voidkin.voidkin.block.blockentity.VoidPedestalBlockEntity;
import net.voidkin.voidkin.render.ModGhostRenderTypes;
import net.voidkin.voidkin.block.ModBlocks;
import org.joml.Vector2i;

import java.util.List;

public class VoidAltarBlockEntityRenderer implements BlockEntityRenderer<VoidAltarBlockEntity> {
    public VoidAltarBlockEntityRenderer(BlockEntityRendererProvider.Context context) {

    }
    List<Vector2i> offsets = List.of(


            new Vector2i(0,3),
            new Vector2i(2,2),
            new Vector2i(3,0),
            new Vector2i(2,-2),

            new Vector2i(0,-3),
            new Vector2i(-2,-2),
            new Vector2i(-3,0),
            new Vector2i(-2,2));

    @Override
    public void render(VoidAltarBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {

        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack stack = pBlockEntity.itemHandler.getStackInSlot(0);

        if (!stack.isEmpty()) {
            double tick = pBlockEntity.getLevel().getGameTime() + pPartialTick;
            pPoseStack.pushPose();
            pPoseStack.translate(0.5f, 1.2f, 0.5f);
            pPoseStack.scale(
                    0.65f,0.65f,0.65f

            );
            //pPoseStack.scale(0.5f, 0.5f, 0.5f);
            pPoseStack.mulPose(Axis.YP.rotationDegrees((float)(tick * 2f) % 360));
            //pPoseStack.mulPose(Axis.YP.rotationDegrees((float)(tick * 10.0D) % 360));
            pPoseStack.translate(0.0D, Math.sin((tick/7) % (2 * Math.PI)) * 0.095D, 0.0D);
            //pPoseStack.rotateAround(Axis.YP.rotationDegrees(13F),0f, 22f, 0f);
            itemRenderer.renderStatic(stack, ItemDisplayContext.GROUND, getLightLevel(pBlockEntity.getLevel(), pBlockEntity.getBlockPos()),
                    OverlayTexture.NO_OVERLAY, pPoseStack, pBuffer, pBlockEntity.getLevel(), 1);
            pPoseStack.popPose();
        }
        offsets.forEach(offset ->{
            if (pBlockEntity.getLevel().getBlockState(pBlockEntity.getBlockPos().offset(offset.x, 0, offset.y)).isAir()){
                renderSidePedestal(pPoseStack,pBuffer,pPackedLight,pPackedOverlay, offset.x, offset.y);
            }
        });


        Level level = pBlockEntity.getLevel();
        if (level == null) return;

        // Only render while crafting
        if (pBlockEntity.progress <= 0 || pBlockEntity.progress >= pBlockEntity.maxProgress) return;

        //ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        // Animation progress 0 → 1
        float t = pBlockEntity.progress / (float) pBlockEntity.maxProgress;
        float eased = (float) Mth.smoothstep(0.0F);

        // Altar center (world space)
        Vec3 altarPos = Vec3.atCenterOf(pBlockEntity.getBlockPos()).add(0, 1.1, 0);

        long time = level.getGameTime();

        for (Vector2i offset : VoidAltarBlockEntity.offsets) {

            BlockPos pedestalPosBlock = pBlockEntity.getBlockPos().offset(offset.x, 0, offset.y);
            BlockEntity pedBE = level.getBlockEntity(pedestalPosBlock);

            if (!(pedBE instanceof VoidPedestalBlockEntity pedestal)) continue;

            ItemStack pStack = pedestal.inventory.getStackInSlot(0);
            if (pStack.isEmpty()) continue;

            // Pedestal center
            Vec3 pedestalPos = Vec3.atCenterOf(pedestalPosBlock).add(0, 0.5, 0);

        /* =========================
           BEAM PARTICLES
           ========================= */
            int segments = 12;
            for (int i = 0; i <= segments; i++) {
                double p = i / (double) segments;
                Vec3 beamPos = pedestalPos.lerp(altarPos, p);

                level.addParticle(
                        ParticleTypes.SOUL_FIRE_FLAME,
                        beamPos.x,
                        beamPos.y,
                        beamPos.z,
                        0,
                        0.01,
                        0
                );
            }

        /* =========================
           ITEM PULL POSITION
           ========================= */
            Vec3 pullPos = pedestalPos.lerp(altarPos, eased);

            // Optional spiral motion
            Vec3 dir = altarPos.subtract(pedestalPos).normalize();
            Vec3 side = new Vec3(-dir.z, 0, dir.x);
            double swirl = Math.sin((time + pPartialTick) * 0.3 + offset.x) * 0.15 * (1 - eased);
            pullPos = pullPos.add(side.scale(swirl));

        /* =========================
           RENDER ITEM
           ========================= */
            pPoseStack.pushPose();

            // Convert world → local renderer space
            pPoseStack.translate(
                    pullPos.x - pBlockEntity.getBlockPos().getX() - 0.5,
                    pullPos.y - pBlockEntity.getBlockPos().getY() - 1.1,
                    pullPos.z - pBlockEntity.getBlockPos().getZ() - 0.5
            );

            pPoseStack.scale(0.4f, 0.4f, 0.4f);
            pPoseStack.mulPose(Axis.YP.rotationDegrees((time + pPartialTick) * 8f));

            /*itemRenderer.renderStatic(
                    stack,
                    ItemDisplayContext.FIXED,
                    pPackedLight,
                    OverlayTexture.NO_OVERLAY,
                    pPoseStack,
                    pBuffer,
                    level,
                    0
            );*/

            pPoseStack.popPose();
        }

//        pPoseStack.popPose();
    }
    private static void renderSidePedestal(PoseStack pPoseStack, MultiBufferSource pBuffer,int pPackedLight, int pPackedOverlay, float xOffset, float zOffset){
        BlockRenderDispatcher blockRenderer = Minecraft.getInstance().getBlockRenderer();
        BlockState state = ModBlocks.VOID_PEDESTAL.get().defaultBlockState();

        VertexConsumer consumer = pBuffer.getBuffer(ModGhostRenderTypes.GHOST);
        //RenderSystem.enableBlend();
        //RenderSystem.defaultBlendFunc();
        //RenderSystem.setShaderColor(1f, 1f, 1f, 0.5f);
//        RenderSystem.disableBlend();


        pPoseStack.pushPose();
        pPoseStack.translate(xOffset, 0f, zOffset);
        pPoseStack.scale(1f, 1f, 1f);



        BakedModel model = blockRenderer.getBlockModel(state);
        blockRenderer.getModelRenderer().renderModel(
                pPoseStack.last(),
                consumer,
                state,
                model,
                1f,1f,1f,
                pPackedLight,
                pPackedOverlay,
                ModelData.EMPTY,
                ModGhostRenderTypes.GHOST);

        pPoseStack.popPose();

    }

    /*public static void renderPullingBeams(PoseStack pPoseStack, MultiBufferSource pBuffer,int pPackedLight, int pPackedOverlay, float xOffset, float zOffset){
        VoidAltarBlockEntity.progress;
        float t = progress / (float) maxProgress; // 0 → 1

        Vec3 altarPos = Vec3.atCenterOf(pBlockEntity.getBlockPos()).add(0, 1.1, 0);

        Vec3 pedestalPos = Vec3.atCenterOf(
                pBlockEntity.getBlockPos().offset(offset.x, 0, offset.y)
        ).add(0, 1.2, 0);
    }*/

    @Override
    public boolean shouldRenderOffScreen(VoidAltarBlockEntity pBlockEntity) {
        return true;/*BlockEntityRenderer.super.shouldRenderOffScreen(pBlockEntity);*/
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}
