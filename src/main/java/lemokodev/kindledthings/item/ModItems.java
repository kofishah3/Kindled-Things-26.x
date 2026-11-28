package lemokodev.kindledthings.item;

import lemokodev.kindledthings.KindledThings;
import lemokodev.kindledthings.entity.ModEntities;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

import java.util.function.Function;

public class ModItems {

    //spawneggs
    public static final Item COPPERBUDDY_SPAWNEGG = registerItem("copper_buddy_spawnegg",
            properties -> new SpawnEggItem(properties.spawnEgg(ModEntities.COPPER_BUDDY)));
    public static final Item SUNFISH_SPAWNEGG = registerItem("sunfish_spawnegg",
            properties -> new SpawnEggItem(properties.spawnEgg(ModEntities.SUNFISH)));

    //blocks
    public static final Item COPPER_COFFER = registerItem("copper_coffer", Item::new);

    //misc items
    public static final Item LUCKY_CLOVER = registerItem("lucky_clover",
            properties -> new Item(properties.component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));

    //food items

    //tools


    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(KindledThings.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(KindledThings.MOD_ID, name)))));
    }

    public static void registerModItems() {
        KindledThings.LOGGER.info("Registering Mod Items for " + KindledThings.MOD_ID);
    }
}
