package lemokodev.kindledthings;

import lemokodev.kindledthings.entity.ModEntities;
import lemokodev.kindledthings.entity.client.copperbuddy.CopperBuddyModel;
import lemokodev.kindledthings.entity.client.copperbuddy.CopperBuddyRenderer;
import lemokodev.kindledthings.entity.client.ModModelLayerLocations;
import lemokodev.kindledthings.entity.client.sunfish.SunFishModel;
import lemokodev.kindledthings.entity.client.sunfish.SunFishRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.renderer.entity.EntityRenderers;

public class KindledThingsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModelLayerRegistry.registerModelLayer(ModModelLayerLocations.COPPER_BUDDY, CopperBuddyModel::createBodyLayer);
        EntityRenderers.register(ModEntities.COPPER_BUDDY, CopperBuddyRenderer::new);

        ModelLayerRegistry.registerModelLayer(ModModelLayerLocations.SUNFISH, SunFishModel::createBodyLayer);
        EntityRenderers.register(ModEntities.SUNFISH, SunFishRenderer::new);
    }
}
