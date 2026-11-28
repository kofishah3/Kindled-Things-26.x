package lemokodev.kindledthings.entity;

import lemokodev.kindledthings.KindledThings;
import lemokodev.kindledthings.entity.custom.CopperBuddyEntity;
import lemokodev.kindledthings.entity.custom.SunFishEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntities {
    public static final ResourceKey<EntityType<?>> COPPER_BUDDY_KEY = ResourceKey.create(Registries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(KindledThings.MOD_ID, "copper_buddy"));

    public static final EntityType<CopperBuddyEntity> COPPER_BUDDY = Registry.register(BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(KindledThings.MOD_ID, "copper_buddy"),
            EntityType.Builder.of(CopperBuddyEntity::new, MobCategory.CREATURE).sized(0.8f, 1f).build(COPPER_BUDDY_KEY));

    public static final ResourceKey<EntityType<?>> SUNFISH_KEY = ResourceKey.create(Registries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(KindledThings.MOD_ID, "sunfish"));

    public static final EntityType<SunFishEntity> SUNFISH = Registry.register(BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(KindledThings.MOD_ID, "sunfish"),
            EntityType.Builder.of(SunFishEntity::new, MobCategory.WATER_AMBIENT).sized(0.5f, 1.2f).build(SUNFISH_KEY));

    public static void registerModEntities() {
        KindledThings.LOGGER.info("Registering Mod Entities for " + KindledThings.MOD_ID);
    }
}
