package lemokodev.kindledthings;

import lemokodev.kindledthings.block.ModBlocks;
import lemokodev.kindledthings.creativemodetab.ModCreativeModeTabs;
import lemokodev.kindledthings.entity.ModEntities;
import lemokodev.kindledthings.entity.custom.CopperBuddyEntity;
import lemokodev.kindledthings.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KindledThings implements ModInitializer {
	public static final String MOD_ID = "kindledthings";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModCreativeModeTabs.registerModCreativeModeTabs();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModEntities.registerModEntities();

		FabricDefaultAttributeRegistry.register(ModEntities.COPPER_BUDDY, CopperBuddyEntity.createAttributes());
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
