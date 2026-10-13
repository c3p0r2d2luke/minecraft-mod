package net.ipv64.frenzi4309;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTabOutput;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.CreativeModeTabs;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;

public class Items {

    public static final Item RUBY = registerRuby();

    public static final Item RUBY_SWORD = registerRubySword();

    public static final Item RUBY_SHOVEL = registerRubyShovel();

    public static final Item RUBY_AXE = registerRubyAxe();

    public static final Item RUBY_PICKAXE = registerRubyPick();

    public static final Item RUBY_HOE = registerRubyHoe();

    private static Item registerRuby() {
        Identifier id = Identifier.fromNamespaceAndPath("hackcraft", "ruby");

        Item item = new Item(
                new Item.Properties()
                        .setId(ResourceKey.create(
                                BuiltInRegistries.ITEM.key(),
                                id
                        ))
        );

        return Registry.register(
                BuiltInRegistries.ITEM,
                id,
                item
        );
    }

    private static Item registerRubySword() {
        Identifier id = Identifier.fromNamespaceAndPath(
                "hackcraft",
                "ruby_sword"
        );

        Item item = new Item(
                new Item.Properties()
                        .setId(ResourceKey.create(
                                BuiltInRegistries.ITEM.key(),
                                id
                        ))
                        .sword(
                                ToolMaterial.DIAMOND,
                                5.0F,
                                -2.4F
                        )
        );

        return Registry.register(
                BuiltInRegistries.ITEM,
                id,
                item
        );
    }

    private static Item registerRubyShovel() {
        Identifier id = Identifier.fromNamespaceAndPath("hackcraft", "ruby_shovel");

        Item item = new Item(
                new Item.Properties()
                        .setId(ResourceKey.create(
                                BuiltInRegistries.ITEM.key(),
                                id
                        ))
        );
        return Registry.register(
                BuiltInRegistries.ITEM,
                id,
                item
        );
    }

    private static Item registerRubyAxe() {
        Identifier id = Identifier.fromNamespaceAndPath("hackcraft", "ruby_axe");
        Item item = new Item(
                new Item.Properties()
                        .setId(ResourceKey.create(
                                BuiltInRegistries.ITEM.key(),
                                id
                        ))
        );
        return Registry.register(
                BuiltInRegistries.ITEM,
                id,
                item
        );
    }

    private static Item registerRubyPick() {
        Identifier id = Identifier.fromNamespaceAndPath("hackcraft", "ruby_pick");
        Item item = new Item(
                new Item.Properties()
                        .setId(ResourceKey.create(
                                BuiltInRegistries.ITEM.key(),
                                id
                        ))
        );
        return Registry.register(
                BuiltInRegistries.ITEM,
                id,
                item
        );
    }

    private static Item registerRubyHoe() {
        Identifier id = Identifier.fromNamespaceAndPath("hackcraft", "ruby_hoe");
        Item item = new Item(
                new Item.Properties()
                        .setId(ResourceKey.create(
                                BuiltInRegistries.ITEM.key(),
                                id
                        ))
        );
        return Registry.register(
                BuiltInRegistries.ITEM,
                id,
                item
        );
    }

    public static void initialize() {
        CreativeModeTabEvents.modifyOutputEvent(
                CreativeModeTabs.INGREDIENTS
        ).register(entries -> entries.accept(RUBY));

        CreativeModeTabEvents.modifyOutputEvent(
                CreativeModeTabs.COMBAT
        ).register(entries -> entries.accept(RUBY_SWORD));

        CreativeModeTabEvents.modifyOutputEvent(
                CreativeModeTabs.TOOLS_AND_UTILITIES
        ).register(entries -> entries.accept(RUBY_AXE));
    }
}