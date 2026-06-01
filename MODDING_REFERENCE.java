// MINECRAFT FABRIC MODDING - QUICK REFERENCE
// Place this file in ~/new-mod/ (next to build.gradle). It will never be compiled.
// ─────────────────────────────────────────────────────────────────────────────
// HOW TO USE THIS FILE:
//   Find what you want to do → copy the import → copy the example code
//
// FIND METHOD NAMES FOR ANY CLASS:
//   https://mcsrc.dev  ← browse Minecraft's actual source code, find method names,
//                        and even get mixin templates generated for you
// ─────────────────────────────────────────────────────────────────────────────

// All imports go at the top of your file, before the class declaration.
// Only import what you actually use — IntelliJ will warn you about unused ones.

package net.ipv64.frenzi4309.mixin; // Must match the file's folder path


// ─────────────────────────────────────────────────────────────────────────────
// ALWAYS NEEDED FOR MIXINS
// ─────────────────────────────────────────────────────────────────────────────
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;            // void methods
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;  // methods that return something

// Bare minimum mixin — hooks into a method and runs your code first:
@Mixin(Item.class)  // ← swap this for whichever class you want (see list below)
public class MyMixin {
    @Inject(method = "use", at = @At("HEAD"))  // ← swap method name as needed
    private void onUse(Level world, Player player, InteractionHand hand,
                       CallbackInfoReturnable<InteractionResult> cir) {
        // your code here
        // cir.cancel() — stops the original method from running
        // cir.setReturnValue(InteractionResult.SUCCESS) — overrides the return value
    }
}


// ─────────────────────────────────────────────────────────────────────────────
// MIXIN CLASS LIST
// For each entry:  @Mixin(X.class) + import + what you can do + useful methods
// ─────────────────────────────────────────────────────────────────────────────

// ── ITEMS ────────────────────────────────────────────────────────────────────

// @Mixin(Item.class)
// import net.minecraft.world.item.Item;
// Use when: hooking into ANY item's behaviour (right-click, tick, etc.)
// Useful methods:
//   use(Level, Player, InteractionHand)        → right-click in the air
//   useOn(UseOnContext)                        → right-click on a block
//   hurtEnemy(ItemStack, LivingEntity, LivingEntity) → hitting an entity
//   inventoryTick(ItemStack, Level, Entity, int, boolean) → runs every tick while in inventory
// Parameters to change: Level world, Player player, InteractionHand hand
// CallbackInfo type: CallbackInfoReturnable<InteractionResult> for use/useOn
//                    CallbackInfo for inventoryTick

// ── PLAYER ───────────────────────────────────────────────────────────────────

// @Mixin(Player.class)
// import net.minecraft.world.entity.player.Player;
// Use when: hooking into anything the player does
// Useful methods:
//   tick()                                    → runs every game tick for this player
//   attack(Entity)                            → player punches something
//   hurt(DamageSource, float)                 → player takes damage
//   die(DamageSource)                         → player dies
//   jump()                                    → player jumps
//   startSleepInBed(BlockPos)                 → player gets into bed
//   drop(ItemStack, boolean)                  → player drops an item
// Parameters to change: depends on method — check mcsrc.dev for exact signatures
// CallbackInfo type: CallbackInfo for void methods (tick, attack, die, jump)
//                    CallbackInfoReturnable<Boolean> for hurt

// @Mixin(ServerPlayer.class)
// import net.minecraft.server.level.ServerPlayer;
// Use when: you need server-only player behaviour (sending packets, respawning)
// Useful methods:
//   doTick()                                  → server-side tick
//   restoreFrom(ServerPlayer, boolean)        → called on respawn — copy data over here
//   sendSystemMessage(Component)              → send a chat message
//   teleportTo(double, double, double)        → teleport the player
// CallbackInfo type: CallbackInfo for most, check mcsrc.dev for exceptions
// Note: extends Player.class — you can use all Player methods here too

// ── ENTITIES ─────────────────────────────────────────────────────────────────

// @Mixin(LivingEntity.class)
// import net.minecraft.world.entity.LivingEntity;
// Use when: anything that applies to ALL living things (players AND mobs)
// Useful methods:
//   tick()                                    → runs every tick
//   hurt(DamageSource, float)                 → takes damage
//   die(DamageSource)                         → dies
//   heal(float)                               → heals
//   knockback(double, double, double)         → gets knocked back
//   travel(Vec3)                              → moves through the world
// CallbackInfo type: CallbackInfo for void methods
//                    CallbackInfoReturnable<Boolean> for hurt

// @Mixin(Mob.class)
// import net.minecraft.world.entity.Mob;
// Use when: hooking into AI-controlled mobs specifically (not players)
// Useful methods:
//   tick()                                    → runs every tick
//   doHurtTarget(Entity)                      → mob attacks something
//   checkDespawn()                            → decides if the mob should despawn
//   setTarget(LivingEntity)                   → mob picks a new attack target
// CallbackInfo type: CallbackInfo for all of the above

// @Mixin(Entity.class)
// import net.minecraft.world.entity.Entity;
// Use when: hooking into behaviour shared by EVERYTHING (players, mobs, items, boats...)
// Useful methods:
//   tick()                                    → runs every tick
//   move(MoverType, Vec3)                     → entity moves
//   onFall(double, boolean)                   → entity lands on something (1.21.5+: no fall damage here, use LivingEntity)
//   isInWater()                               → check if in water
//   thunderHit(ServerLevel, LightningBolt)    → struck by lightning
// CallbackInfo type: CallbackInfo for all of the above

// ── BLOCKS ───────────────────────────────────────────────────────────────────

// @Mixin(Block.class)
// import net.minecraft.world.level.block.Block;
// Use when: changing how any block behaves
// Useful methods:
//   playerDestroy(Level, Player, BlockPos, BlockState, BlockEntity, ItemStack) → block broken by player
//   stepOn(Level, BlockPos, BlockState, Entity) → entity walks over the block
//   attack(BlockState, Level, BlockPos, Player) → player left-clicks the block
//   use(BlockState, Level, BlockPos, Player, InteractionHand, BlockHitResult) → player right-clicks
// CallbackInfo type: CallbackInfo for playerDestroy, stepOn, attack
//                    CallbackInfoReturnable<InteractionResult> for use

// @Mixin(LevelChunk.class)  (or ServerLevel.class)
// import net.minecraft.world.level.chunk.LevelChunk;
// Use when: hooking into chunk or world-level events
// Useful methods (ServerLevel):
//   tick(BooleanSupplier)                     → world tick
//   setBlock(BlockPos, BlockState, int)       → a block changes
//   explode(...)                              → an explosion happens

// ── MINECRAFT CLIENT (client-side only) ──────────────────────────────────────

// @Mixin(Minecraft.class)
// import net.minecraft.client.Minecraft;
// Use when: changing client-side behaviour (HUD, rendering, key input)
// Useful methods:
//   tick()                                    → client tick (runs 20x/sec)
//   handleKeybinds()                          → key presses are processed here
//   disconnect()                              → player leaves a world/server
// CallbackInfo type: CallbackInfo for all of the above
// NOTE: only use this in client-side mixins (put in the "client" array in mixins.json)


// ─────────────────────────────────────────────────────────────────────────────
// THINGS TO CHANGE PER MIXIN (quick checklist)
// ─────────────────────────────────────────────────────────────────────────────
// 1. @Mixin(X.class)          ← the class you're targeting
// 2. import for that class    ← add the right import at the top
// 3. method = "methodName"    ← the method inside that class you want to hook
// 4. @At("HEAD"/"TAIL"/...)   ← when to run your code inside that method
// 5. Method parameters        ← must match the real method's parameters exactly
// 6. CallbackInfo type        ← CallbackInfo (void) or CallbackInfoReturnable<T>
// 7. Class name               ← rename your mixin class (e.g. PlayerMixin)
// 8. mixins.json              ← add your new class name to the list there


// ─────────────────────────────────────────────────────────────────────────────
// PLAYER
// ─────────────────────────────────────────────────────────────────────────────
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionHand;   // MAIN_HAND or OFF_HAND
import net.minecraft.world.item.ItemStack;    // an item + how many + its data
import net.minecraft.world.item.Items;        // every vanilla item: Items.DIAMOND_SWORD, Items.APPLE...
import net.minecraft.network.chat.Component;  // for building chat messages

// Send a chat message:
player.sendSystemMessage(Component.literal("Hello!"));

        // Check what they're holding:
        ItemStack stack = player.getItemInHand(hand);
if (stack.is(Items.DIAMOND_SWORD)) { /* holding a diamond sword */ }
        if (stack.isEmpty()) { /* holding nothing */ }

// Give them an item:
        player.getInventory().add(new ItemStack(Items.DIAMOND, 5)); // 5 diamonds

// Heal or hurt them:
        player.heal(4.0f);                                    // 4.0 = 2 hearts healed
player.hurt(world.damageSources().generic(), 5.0f);   // 5.0 = 2.5 hearts damage


// ─────────────────────────────────────────────────────────────────────────────
// POTION EFFECTS
// ─────────────────────────────────────────────────────────────────────────────
        import net.minecraft.world.effect.MobEffects;       // MOVEMENT_SPEED, STRENGTH, REGENERATION...
import net.minecraft.world.effect.MobEffectInstance;

// Give Speed II for 10 seconds:
// new MobEffectInstance(effect, durationInTicks, amplifier)
// 20 ticks = 1 second | amplifier 0 = level I, 1 = level II
player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 1));


// ─────────────────────────────────────────────────────────────────────────────
// WORLD / SERVER vs CLIENT
// ─────────────────────────────────────────────────────────────────────────────
        import net.minecraft.world.level.Level;

// Minecraft runs two sides at once. Always check before doing game logic:
if (!world.isClientSide()) { /* SERVER — do damage, effects, messages here */ }
        if (world.isClientSide())  { /* CLIENT — do visuals, sounds, particles here */ }


// ─────────────────────────────────────────────────────────────────────────────
// BLOCKS
// ─────────────────────────────────────────────────────────────────────────────
        import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;

        BlockPos pos = new BlockPos(x, y, z);
        BlockState block = world.getBlockState(pos);
if (block.is(Blocks.DIRT)) { /* it's dirt */ }
        world.setBlock(pos, Blocks.DIAMOND_BLOCK.defaultBlockState(), 3); // place a block


// ─────────────────────────────────────────────────────────────────────────────
// ENTITIES
// ─────────────────────────────────────────────────────────────────────────────
        import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity; // anything alive (players, mobs)
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.lightning.LightningBolt;

// Check if something is a player before treating it like one:
if (entity instanceof Player player) { player.sendSystemMessage(Component.literal("hi")); }

        // Spawn lightning at a position:
        LightningBolt bolt = new LightningBolt(EntityType.LIGHTNING_BOLT, world);
bolt.setPos(player.getX(), player.getY(), player.getZ());
        world.addFreshEntity(bolt);


// ─────────────────────────────────────────────────────────────────────────────
// FABRIC EVENTS (alternative to Mixins — goes in your main mod class)
// ─────────────────────────────────────────────────────────────────────────────
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;

// Every tick (20x per second):
ServerTickEvents.END_SERVER_TICK.register(server -> { });

// Player joins:
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
        handler.player.sendSystemMessage(Component.literal("Welcome!"));
        });

// Player breaks a block:
        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> { });

// Entity dies:
        ServerLivingEntityEvents.AFTER_DEATH.register((entity, damageSource) -> {
        if (entity instanceof Player player) { /* a player died */ }
        });


// ─────────────────────────────────────────────────────────────────────────────
// JAVA CHEATSHEET
// ─────────────────────────────────────────────────────────────────────────────

        // Variables:
        int    x    = 5;       // whole number
        float  y    = 3.14f;   // decimal (needs the f)
        double z    = 3.14;    // more precise decimal
        boolean b   = true;    // true or false
        String  s   = "hello"; // text

// Conditions:
// !x       NOT x
// x && y   x AND y (both must be true)
// x || y   x OR y  (either can be true)

// Loops:
for (int i = 0; i < 10; i++) { }          // runs 10 times
        for (Item item : itemList) { }             // loop over a list
        while (condition) { }                      // runs until condition is false

// Null safety — always check before using something that might not exist:
        if (thing == null) return;   // exit early
        if (thing != null) { }       // safe to use