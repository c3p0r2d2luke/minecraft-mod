// =============================================================================
// MINECRAFT FABRIC MODDING - REFERENCE FILE
// =============================================================================
// This file is for reference only. It will NOT compile or affect your mod.
// Put it anywhere in your project outside of src/ — for example:
//   ~/new-mod/MODDING_REFERENCE.java
// Java only compiles files inside src/main/java/ that are part of your build,
// so this file sitting in the root of your project is completely harmless.
// =============================================================================


// ========================
// 1. PACKAGE & IMPORTS
// ========================

// Every .java file needs a package declaration matching its folder location.
// If your file is at: src/main/java/net/ipv64/frenzi4309/mixin/MyMixin.java
// Then your package is:
package net.ipv64.frenzi4309.mixin;

// Imports tell Java about classes you want to use.
// You must import anything that isn't in java.lang (which is auto-imported).

// --- COMMON MINECRAFT IMPORTS ---
import net.minecraft.world.entity.player.Player;         // The player object
import net.minecraft.world.item.Item;                    // Base class for all items
import net.minecraft.world.item.ItemStack;               // An item + its count + NBT data
import net.minecraft.world.item.Items;                   // Constants for every vanilla item (Items.DIAMOND_SWORD, Items.APPLE, etc.)
import net.minecraft.world.InteractionHand;              // Which hand: MAIN_HAND or OFF_HAND
import net.minecraft.world.InteractionResult;            // What to return after a use() call
import net.minecraft.world.level.Level;                  // The game world
import net.minecraft.network.chat.Component;             // Used to build chat/text messages
import net.minecraft.world.effect.MobEffects;            // Vanilla potion effects (SPEED, STRENGTH, etc.)
import net.minecraft.world.effect.MobEffectInstance;     // A potion effect with duration + amplifier
import net.minecraft.world.entity.LivingEntity;          // Any living thing (players, mobs, etc.)
import net.minecraft.world.entity.Entity;                // Any entity in the world
import net.minecraft.core.BlockPos;                      // An X/Y/Z position in the world
import net.minecraft.world.level.block.state.BlockState; // The state of a block (type + properties)
import net.minecraft.world.damagesource.DamageSource;    // What caused damage (fall, fire, player, etc.)

// --- MIXIN IMPORTS (always needed for mixins) ---
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;             // For void methods
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;   // For methods that return a value


// ========================
// 2. MIXIN BASICS
// ========================

// @Mixin(SomeClass.class) — tells Mixin which Minecraft class to inject into.
// Your class "becomes" that class at runtime.
@Mixin(Item.class)
public class MODDING_REFERENCE {

    // @Inject — the most common Mixin tool. Inserts your code into an existing method.
    //   method = "methodName"  → which method to target
    //   at = @At("HEAD")       → where in that method to inject
    //
    // Common @At positions:
    //   @At("HEAD")    — start of the method, before anything runs
    //   @At("TAIL")    — end of the method, after everything runs
    //   @At("RETURN")  — just before the method returns its value

    // Use CallbackInfo (no return value) for void methods:
    @Inject(method = "someVoidMethod", at = @At("HEAD"))
    private void myInjection(CallbackInfo ci) {
        // your code here
        // ci.cancel() — call this to stop the original method from running
    }

    // Use CallbackInfoReturnable for methods that return something:
    @Inject(method = "use", at = @At("HEAD"))
    private void onUse(Level world, Player player, InteractionHand hand,
                       CallbackInfoReturnable<InteractionResult> cir) {
        // your code here
        // cir.setReturnValue(InteractionResult.SUCCESS) — override what the method returns
        // cir.cancel() — stop the original method entirely
    }


    // ========================
    // 3. COMMON THINGS TO DO
    // ========================

    // --- Send a chat message to the player ---
    private void exampleSendMessage(Player player) {
        player.sendSystemMessage(Component.literal("Hello!"));

        // With color (using Style):
        // player.sendSystemMessage(Component.literal("Red text").withStyle(ChatFormatting.RED));
    }

    // --- Give the player a potion effect ---
    private void exampleGiveEffect(Player player) {
        // new MobEffectInstance(effect, durationInTicks, amplifier)
        // 20 ticks = 1 second. Amplifier 0 = level I, 1 = level II, etc.
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 1));
        //                                      ^ Speed II for 10 seconds (200 ticks)
    }

    // --- Check what item the player is holding ---
    private void exampleCheckItem(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (stack.is(Items.DIAMOND_SWORD)) {
            // player is holding a diamond sword
        }

        if (stack.isEmpty()) {
            // player is holding nothing
        }
    }

    // --- Deal damage to the player ---
    private void exampleDealDamage(Player player, Level world) {
        // hurt(damageSource, amount)
        player.hurt(world.damageSources().generic(), 5.0f);
        //                                            ^ 5.0 = 2.5 hearts
    }

    // --- Heal the player ---
    private void exampleHeal(Player player) {
        player.heal(4.0f); // 4.0 = 2 hearts
    }

    // --- Check server vs client side ---
    private void exampleSideCheck(Level world) {
        if (!world.isClientSide()) {
            // SERVER side — game logic lives here (health, damage, effects, etc.)
        }
        if (world.isClientSide()) {
            // CLIENT side — rendering and visual effects live here
        }
        // Rule of thumb: always do game logic on the server side (!isClientSide)
    }

    // --- Get a block at a position ---
    private void exampleGetBlock(Level world) {
        BlockPos pos = new BlockPos(0, 64, 0); // X, Y, Z
        BlockState block = world.getBlockState(pos);

        // Check what block it is:
        // if (block.is(Blocks.DIRT)) { ... }
    }

    // --- Spawn lightning at the player's location ---
    private void exampleSpawnLightning(Player player, Level world) {
        // LightningBolt bolt = new LightningBolt(EntityType.LIGHTNING_BOLT, world);
        // bolt.setPos(player.getX(), player.getY(), player.getZ());
        // world.addFreshEntity(bolt);
    }

    // --- Give the player an item ---
    private void exampleGiveItem(Player player) {
        ItemStack newStack = new ItemStack(Items.DIAMOND, 5); // 5 diamonds
        player.getInventory().add(newStack);
    }


    // ========================
    // 4. COMMON EVENTS (Fabric API)
    // ========================

    // Instead of Mixins, Fabric API provides events you can hook into cleanly.
    // These go in your main mod class (the one that implements ModInitializer).

    // --- Run code every server tick ---
    // ServerTickEvents.END_SERVER_TICK.register(server -> {
    //     // runs 20 times per second
    // });

    // --- Run code when a player joins ---
    // ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
    //     handler.player.sendSystemMessage(Component.literal("Welcome!"));
    // });

    // --- Run code when a player dies ---
    // ServerLivingEntityEvents.AFTER_DEATH.register((entity, damageSource) -> {
    //     if (entity instanceof Player player) {
    //         // a player died
    //     }
    // });

    // --- Run code when a block is broken ---
    // PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
    //     // player broke a block
    // });


    // ========================
    // 5. JAVA QUICK REFERENCE
    // ========================

    // --- Variables ---
    // int x = 5;           whole numbers
    // float y = 3.14f;     decimal numbers (need the f at the end)
    // double z = 3.14;     more precise decimals
    // boolean flag = true; true or false
    // String s = "hello";  text

    // --- If statements ---
    // if (condition) { } else if (condition) { } else { }
    // ! means NOT:  !true == false
    // && means AND: (a && b) — both must be true
    // || means OR:  (a || b) — either can be true

    // --- Loops ---
    // for (int i = 0; i < 10; i++) { }        runs 10 times
    // while (condition) { }                    runs until condition is false
    // for (Item item : itemList) { }           loops over every item in a list

    // --- instanceof (check + cast in one step, modern Java) ---
    // if (entity instanceof Player player) {
    //     player.sendSystemMessage(...);  // entity is now usable as a Player
    // }

    // --- Null checks (avoid NullPointerException) ---
    // if (thing != null) { }
    // or use:  if (thing == null) return;   to exit early


    // ========================
    // 6. PROJECT STRUCTURE REMINDER
    // ========================

    // src/main/java/net/ipv64/frenzi4309/
    //   ├── YourMod.java                  ← main mod class (ModInitializer)
    //   └── mixin/
    //       └── YourMixin.java            ← mixin files go here
    //
    // src/main/resources/
    //   ├── fabric.mod.json               ← mod metadata (name, id, version)
    //   └── yourmod.mixins.json           ← register your mixin classes here
    //
    // build.gradle                        ← dependencies and build config
    // gradle.properties                   ← minecraft version, fabric version, mod version


    // ========================
    // 7. MIXINS JSON REMINDER
    // ========================

    // Every mixin class must be listed in yourmod.mixins.json or it won't load:
    //
    // {
    //   "required": true,
    //   "package": "net.ipv64.frenzi4309.mixin",
    //   "compatibilityLevel": "JAVA_25",
    //   "mixins": [
    //     "DiamondSwordMixin",
    //     "AnotherMixin"
    //   ],
    //   "injectors": {
    //     "defaultRequire": 1
    //   }
    // }

}