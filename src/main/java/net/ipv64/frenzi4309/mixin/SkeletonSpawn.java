
package net.ipv64.frenzi4309.mixin;

import net.ipv64.frenzi4309.entity.SkeletonWolfEntity;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.monster.skeleton.Skeleton;
import net.minecraft.world.level.ServerLevelAccessor;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Mob.class)
public abstract class SkeletonSpawn {

    @Inject(method = "finalizeSpawn", at = @At("RETURN"))
    private void onSkeletonSpawn(
        ServerLevelAccessor level,
        DifficultyInstance difficulty,
        EntitySpawnReason spawnReason,
        SpawnGroupData spawnGroupData,
        CallbackInfoReturnable<SpawnGroupData> cir
    ) {
        Mob mob = (Mob) (Object) this;

        // Only affect skeletons
        if (!(mob instanceof Skeleton skeleton)) {
            return;
        }

        // 15% chance to spawn a skeleton wolf companion
        if (!level.isClientSide() && level.getRandom().nextFloat() < 0.15F) {

            SkeletonWolfEntity wolf = SkeletonWolfEntity.TYPE.create(
                skeleton.level(),
                EntitySpawnReason.EVENT
            );

            if (wolf != null) {
                wolf.setPos(
                    skeleton.getX(),
                    skeleton.getY(),
                    skeleton.getZ()
                );

                wolf.setYRot(skeleton.getYRot());
                wolf.setXRot(skeleton.getXRot());

                wolf.setSkeletonOwner(skeleton);

                level.addFreshEntity(wolf);
            }
        }
    }
}
