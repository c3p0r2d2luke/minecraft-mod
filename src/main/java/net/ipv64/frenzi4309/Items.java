package net.ipv64.frenzi4309;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class Items {

    public static final Item RUBY = register("ruby");

    private static Item register(String name) {
        Identifier id = Identifier.fromNamespaceAndPath("hackcraft", name);

        Item item = new Item(
                new Item.Properties()
                        .setId(
                                ResourceKey.create(
                                        BuiltInRegistries.ITEM.key(),
                                        id
                                )
                        )
                        .attributes(
                                ItemAttributeModifiers.builder()
                                        .add(
                                                Attributes.ATTACK_DAMAGE,
                                                new AttributeModifier(
                                                        Identifier.fromNamespaceAndPath("hackcraft", "base_attack_damage"),
                                                        7.0, // 7.0 bonus damage + 1.0 base bare-hand damage = 8.0 (4 hearts)
                                                        AttributeModifier.Operation.ADD_VALUE
                                                ),
                                                EquipmentSlotGroup.MAINHAND
                                        )
                                        .build()
                        )
        );

        return Registry.register(
                BuiltInRegistries.ITEM,
                id,
                item
        );
    }

    public static void initialize() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register(entries -> {
            entries.accept(RUBY);
        });
    }
}