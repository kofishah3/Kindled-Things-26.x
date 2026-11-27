package lemokodev.kindledthings.block;

import lemokodev.kindledthings.KindledThings;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;

public class ModBlocks {

    public static final Block TEST_BLOCK = registerBlock("test_block",
            properties -> new Block(properties
                    .strength(3f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
                    .ignitedByLava()
            ));

    public static final Block NETHER_FLUORITE_ORE = registerBlock("nether_fluorite_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2, 5), properties
                    .strength(4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.ANCIENT_DEBRIS)
                    .ignitedByLava()
            ));

    public static final Block END_FLUORITE_ORE = registerBlock("end_fluorite_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2, 5), properties
                    .strength(4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
                    .ignitedByLava()
            ));


    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function) {
        Block toRegister = function.apply(BlockBehaviour.Properties.
                of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(KindledThings.MOD_ID, name))));
        registerBlockItem(name, toRegister);

        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(KindledThings.MOD_ID, name), toRegister);

    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(KindledThings.MOD_ID, name),
                new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(KindledThings.MOD_ID, name)))));
    }

    public static void registerModBlocks() {
        KindledThings.LOGGER.info("Registering Mod Blocks for " + KindledThings.MOD_ID);
    }
}
