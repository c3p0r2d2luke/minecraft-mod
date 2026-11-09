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

        // Server side only
        if (wolf.level().isClientSide()) {
            return;
        }

        // Prevent skeleton wolves from creating more skeleton wolves
        if (wolf instanceof SkeletonWolfEntity) {
            return;
        }

        // Only transform tamed wolves
        if (!wolf.isTame()) {
            return;
        }

        SkeletonWolfEntity skeletonWolf = SkeletonWolfEntity.TYPE.create(
                wolf.level(),
                EntitySpawnReason.EVENT
        );

        if (skeletonWolf == null) {
            return;
        }

        // Preserve position and rotation
        skeletonWolf.setPos(wolf.getX(), wolf.getY(), wolf.getZ());
        skeletonWolf.setYRot(wolf.getYRot());
        skeletonWolf.setXRot(wolf.getXRot());

        // Transfer ownership
        if (wolf.getOwner() instanceof Player player) {
            skeletonWolf.tame(player);
        } else {
            skeletonWolf.setTame(true, true);
        }

        // Preserve custom name
        if (wolf.hasCustomName()) {
            skeletonWolf.setCustomName(wolf.getCustomName());
        }

        // Spawn replacement
        wolf.level().addFreshEntity(skeletonWolf);
    }
}
