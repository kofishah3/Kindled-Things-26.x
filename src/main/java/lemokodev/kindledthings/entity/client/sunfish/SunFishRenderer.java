package lemokodev.kindledthings.entity.client.sunfish;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import lemokodev.kindledthings.KindledThings;
import lemokodev.kindledthings.entity.client.ModModelLayerLocations;
import lemokodev.kindledthings.entity.custom.SunFishEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

public class SunFishRenderer extends MobRenderer<SunFishEntity, SunFishRenderState, SunFishModel> {
    public SunFishRenderer(EntityRendererProvider.Context context) {
        super(context, new SunFishModel(context.bakeLayer(ModModelLayerLocations.SUNFISH)), 0.3f);
    }

    @Override
    public SunFishRenderState createRenderState() {
        return new SunFishRenderState();
    }

    @Override
    public Identifier getTextureLocation(SunFishRenderState state) {
        return Identifier.fromNamespaceAndPath(KindledThings.MOD_ID, "textures/entity/sunfish/sunfish.png");
    }

    @Override
    protected void scale(SunFishRenderState state, PoseStack poseStack) {
        poseStack.scale(state.sunfishScale, state.sunfishScale, state.sunfishScale);
    }

    @Override
    public void extractRenderState(SunFishEntity entity, SunFishRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);

        state.sunfishScale = entity.getSunfishScale();
        state.idleAnimationState.copyFrom(entity.idleAnimationState);
    }

    @Override
    protected void setupRotations(SunFishRenderState state, PoseStack poseStack, float bodyRot, float entityScale) {
        super.setupRotations(state, poseStack, bodyRot, entityScale);

        float wiggle = (state.isInWater ? 1.0F : 1.3F) * 4.3F * Mth.sin((state.isInWater ? 1.0F : 1.7F) * 0.6F * state.ageInTicks);
        poseStack.rotateDegrees(Axis.YP, wiggle);

        if(!state.isInWater) {
            float s = state.sunfishScale;
            poseStack.translate(0.6F * s, 0.125F * s, 0.0F);
            poseStack.rotateDegrees(Axis.ZP, 90.0F);
        }
    }
}
