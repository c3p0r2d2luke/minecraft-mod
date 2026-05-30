// This declares what "package" (folder/namespace) this file belongs to.
// It must match the actual folder path of the file on disk.
package net.ipv64.frenzi4309.mixin;

// Imports tell Java about other classes we want to use.
// Without these, Java wouldn't know what "Player" or "ItemStack" means.
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.Level;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// @Mixin tells the Mixin framework that this class is going to inject code
// into Minecraft's existing Item class. We're not editing Minecraft's code
// directly — Mixin stitches our code in at runtime.
@Mixin(Item.class)
public class DiamondSwordMixin {

    // @Inject tells Mixin exactly where to insert our code:
    //   method = "use"     → target the "use" method inside Item (called when a player right-clicks)
    //   at = @At("HEAD")   → run our code at the very START of that method, before anything else
    @Inject(method = "use", at = @At("HEAD"))
    private void onUse(
            Level world,        // The game world — lets us check things like server vs client
            Player player,      // The player who right-clicked
            InteractionHand hand, // Which hand they used (main hand or off hand)
            CallbackInfoReturnable<InteractionResult> cir  // Required by Mixin — lets you cancel
            // or modify the original method's return
            // value if needed. We don't use it here.
    ) {

        // Ask the player: "what item are you holding in the hand you just used?"
        // Store the result in a variable called "stack"
        ItemStack stack = player.getItemInHand(hand);

        // Check if the item in that hand is specifically a diamond sword.
        // Items.DIAMOND_SWORD is a constant Minecraft provides for every vanilla item.
        if (stack.is(Items.DIAMOND_SWORD)) {

            // Minecraft runs two sides at once: the CLIENT (what you see on screen)
            // and the SERVER (where the actual game logic lives).
            // isClientSide() returns true if we're on the client.
            // The ! means "NOT" — so this block only runs on the SERVER side.
            // We do this to avoid the message appearing twice (once per side).
            if (!world.isClientSide()) {

                // Send a chat message to the player.
                // Component.literal() wraps a plain string into the format
                // Minecraft's chat system requires.
                player.sendSystemMessage(
                        Component.literal("You right-clicked with a Diamond Sword!")
                );
            }
        }
    }
}