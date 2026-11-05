package net.ipv64.frenzi4309.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.animal.wolf.Wolf;
import net.minecraft.world.level.Level;

@SuppressWarnings({"null"})

public class SkeletonWolfEntity extends Wolf {
    public static final EntityType<SkeletonWolfEntity> TYPE = registerType();

    public SkeletonWolfEntity(EntityType<? extends SkeletonWolfEntity> type, Level level) {
        super(type, level);
    }

    public static void initialize() {
        FabricDefaultAttributeRegistry.register(TYPE, Wolf.createAttributes());
    }

    private static EntityType<SkeletonWolfEntity> registerType() {
        Identifier id = Identifier.fromNamespaceAndPath("hackcraft", "skeleton_wolf");
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, id);
        EntityType<SkeletonWolfEntity> type = EntityType.Builder
                .of(SkeletonWolfEntity::new, MobCategory.CREATURE)
                .sized(0.6F, 0.85F)
                .clientTrackingRange(10)
                .build(key);
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, id, type);
    }
}
