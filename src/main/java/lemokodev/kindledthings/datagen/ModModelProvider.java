package lemokodev.kindledthings.datagen;

import lemokodev.kindledthings.block.ModBlocks;
import lemokodev.kindledthings.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.createTrivialCube(ModBlocks.TEST_BLOCK);
        blockModelGenerators.createTrivialCube(ModBlocks.NETHER_FLUORITE_ORE);
        blockModelGenerators.createTrivialCube(ModBlocks.END_FLUORITE_ORE);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(ModItems.COPPERBUDDY_SPAWNEGG, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.COPPER_COFFER, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.SUNFISH_SPAWNEGG, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.LUCKY_CLOVER, ModelTemplates.FLAT_ITEM);
    }
}
