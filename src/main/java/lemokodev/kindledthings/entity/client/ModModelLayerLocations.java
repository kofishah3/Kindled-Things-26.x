package lemokodev.kindledthings.entity.client;

import lemokodev.kindledthings.KindledThings;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.Identifier;

public class ModModelLayerLocations {
    public static final ModelLayerLocation COPPER_BUDDY =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(KindledThings.MOD_ID, "copper_buddy"), "main");
}
