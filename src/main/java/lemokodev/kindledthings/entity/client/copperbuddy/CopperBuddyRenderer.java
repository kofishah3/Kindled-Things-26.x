package lemokodev.kindledthings.entity.client.copperbuddy;

import lemokodev.kindledthings.KindledThings;
import lemokodev.kindledthings.entity.client.ModModelLayerLocations;
import lemokodev.kindledthings.entity.custom.CopperBuddyEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class CopperBuddyRenderer extends MobRenderer<CopperBuddyEntity, CopperBuddyRenderState, CopperBuddyModel> {
    public CopperBuddyRenderer(EntityRendererProvider.Context context) {
        super(context, new CopperBuddyModel(context.bakeLayer(ModModelLayerLocations.COPPER_BUDDY)), 0.3f);
    }

    @Override
    public Identifier getTextureLocation(CopperBuddyRenderState state) {
        return Identifier.fromNamespaceAndPath(KindledThings.MOD_ID, "textures/entity/copper_buddy/copper_buddy.png");
    }

    @Override
    public CopperBuddyRenderState createRenderState() {
        return new CopperBuddyRenderState();
    }

    @Override
    public void extractRenderState(CopperBuddyEntity entity, CopperBuddyRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);

        state.idleAnimationState.copyFrom(entity.idleAnimationState);
        state.idleLookAroundAnimationState.copyFrom(entity.idleLookAroundAnimationState);
    }
}
