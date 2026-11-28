package lemokodev.kindledthings.creativemodetab;

import lemokodev.kindledthings.KindledThings;
import lemokodev.kindledthings.block.ModBlocks;
import lemokodev.kindledthings.item.ModItems;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTabs {
    public static final CreativeModeTab KINDLED_THINGS_ITEM_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(KindledThings.MOD_ID, "kindled_things_items"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModItems.COPPERBUDDY_SPAWNEGG))
                    .title(Component.translatable("creativemodetab.kindledthings.kindled_things_items"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.COPPERBUDDY_SPAWNEGG);
                        output.accept(ModItems.SUNFISH_SPAWNEGG);
                        output.accept(ModItems.COPPER_COFFER);
                        output.accept(ModItems.LUCKY_CLOVER);
                    }).build());

    public static final CreativeModeTab KINDLED_THINGS_BLOCKS_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(KindledThings.MOD_ID, "kindled_things_blocks"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.TEST_BLOCK))
                    .title(Component.translatable("creativemodetab.kindledthings.kindled_things_blocks"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModBlocks.TEST_BLOCK);
                        output.accept(ModBlocks.NETHER_FLUORITE_ORE);
                        output.accept(ModBlocks.END_FLUORITE_ORE);
                    }).build());

    public static void registerModCreativeModeTabs() {
        KindledThings.LOGGER.info("Registering Creative Mode Tabs for " + KindledThings.MOD_ID);
    }

}
