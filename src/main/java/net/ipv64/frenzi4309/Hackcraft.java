package net.ipv64.frenzi4309;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.ipv64.frenzi4309.entity.SkeletonWolfEntity;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;

public class Hackcraft implements ModInitializer {

    @Override
    public void onInitialize() {
        SkeletonWolfEntity.initialize();
        Items.initialize();
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (BuiltInLootTables.BURIED_TREASURE.equals(key)) {
                tableBuilder.withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(Items.RUBY))
                        .when(LootItemRandomChanceCondition.randomChance(0.1F)));
            }
        });
    }
}