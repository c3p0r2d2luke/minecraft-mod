package net.ipv64.frenzi4309.mixin;

import net.ipv64.frenzi4309.entity.SkeletonWolfEntity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.animal.wolf.Wolf;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Wolf.class)
public abstract class WolfDeath {

    @Inject(method = "die", at = @At("HEAD"))
    private void onTamedWolfDie(DamageSource damageSource, CallbackInfo ci) {
        Wolf wolf = (Wolf) (Object) this;

        if (!wolf.level().isClientSide() && wolf.isTame()) {
            SkeletonWolfEntity skeletonWolf = SkeletonWolfEntity.TYPE.create(wolf.level(), EntitySpawnReason.EVENT);
            
            if (skeletonWolf != null) {
                // Set position and orientation
                skeletonWolf.setPos(wolf.getX(), wolf.getY(), wolf.getZ());
                skeletonWolf.setYRot(wolf.getYRot());
                skeletonWolf.setXRot(wolf.getXRot());
                
                // Transfer ownership to player
                if (wolf.getOwner() instanceof Player player) {
                    skeletonWolf.tame(player);
                } else {
                    skeletonWolf.setTame(true, true);
                }
                
                // Transfer custom name
                if (wolf.hasCustomName()) {
                    skeletonWolf.setCustomName(wolf.getCustomName());
                }

                wolf.level().addFreshEntity(skeletonWolf);
            }
        }
    }
}