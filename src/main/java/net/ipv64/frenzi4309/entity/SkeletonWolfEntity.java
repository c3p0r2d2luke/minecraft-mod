
package net.ipv64.frenzi4309.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.animal.wolf.Wolf;
import net.minecraft.world.entity.monster.skeleton.Skeleton;
import net.minecraft.world.level.Level;

import java.util.EnumSet;

@SuppressWarnings({"null"})
public class SkeletonWolfEntity extends Wolf {

    public static final EntityType<SkeletonWolfEntity> TYPE = registerType();

    private Skeleton skeletonOwner;

    public SkeletonWolfEntity(EntityType<? extends SkeletonWolfEntity> type, Level level) {
        super(type, level);
    }

    public void setSkeletonOwner(Skeleton skeleton) {
        this.skeletonOwner = skeleton;
    }

    public Skeleton getSkeletonOwner() {
        return this.skeletonOwner;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        // Follow the skeleton when it gets more than 10 blocks away.
        this.goalSelector.addGoal(6, new FollowSkeletonGoal(this, 1.25));

        // Sit when the skeleton is idle and nearby.
        this.goalSelector.addGoal(7, new SkeletonIdleSitGoal(this));

        // Attack whatever the skeleton attacks.
        this.targetSelector.addGoal(1, new SkeletonTargetGoal(this));
    }

    public static void initialize() {
        FabricDefaultAttributeRegistry.register(TYPE, createSkeletonWolfAttributes());
    }

    public static AttributeSupplier.Builder createSkeletonWolfAttributes() {
        return Wolf.createAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.ARMOR, 4.0)
                .add(Attributes.ATTACK_DAMAGE, 5.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3);
    }

    private static EntityType<SkeletonWolfEntity> registerType() {
        Identifier id = Identifier.fromNamespaceAndPath("hackcraft", "skeleton_wolf");

        ResourceKey<EntityType<?>> key =
                ResourceKey.create(Registries.ENTITY_TYPE, id);

        EntityType<SkeletonWolfEntity> type = EntityType.Builder
                .of(SkeletonWolfEntity::new, MobCategory.CREATURE)
                .sized(0.6F, 0.85F)
                .clientTrackingRange(10)
                .build(key);

        return Registry.register(BuiltInRegistries.ENTITY_TYPE, id, type);
    }

    // Follows the skeleton when it is more than 10 blocks away.
    static class FollowSkeletonGoal extends Goal {

        private final SkeletonWolfEntity wolf;
        private final double speed;

        public FollowSkeletonGoal(SkeletonWolfEntity wolf, double speed) {
            this.wolf = wolf;
            this.speed = speed;

            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            Skeleton owner = this.wolf.getSkeletonOwner();

            return owner != null
                    && owner.isAlive()
                    && this.wolf.distanceToSqr(owner) > 100.0;
        }

        @Override
        public boolean canContinueToUse() {
            Skeleton owner = this.wolf.getSkeletonOwner();

            return owner != null
                    && owner.isAlive()
                    && this.wolf.distanceToSqr(owner) > 100.0;
        }

        @Override
        public void start() {
            this.wolf.setOrderedToSit(false);
        }

        @Override
        public void tick() {
            Skeleton owner = this.wolf.getSkeletonOwner();

            if (owner != null) {
                this.wolf.getLookControl().setLookAt(
                        owner,
                        10.0F,
                        (float) this.wolf.getMaxHeadXRot()
                );

                this.wolf.getNavigation().moveTo(owner, this.speed);
            }
        }

        @Override
        public void stop() {
            this.wolf.getNavigation().stop();
        }
    }

    // Makes the skeleton wolf sit when its skeleton is idle and nearby.
    static class SkeletonIdleSitGoal extends Goal {

        private final SkeletonWolfEntity wolf;

        public SkeletonIdleSitGoal(SkeletonWolfEntity wolf) {
            this.wolf = wolf;

            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            Skeleton owner = this.wolf.getSkeletonOwner();

            if (owner == null || !owner.isAlive()) {
                return false;
            }

            // Do not sit during combat.
            if (owner.getTarget() != null) {
                return false;
            }

            // Only sit when the skeleton is within 10 blocks.
            if (this.wolf.distanceToSqr(owner) > 100.0) {
                return false;
            }

            // Check whether the skeleton is essentially stationary.
            return owner.getDeltaMovement().horizontalDistanceSqr() < 0.0025;
        }

        @Override
        public boolean canContinueToUse() {
            Skeleton owner = this.wolf.getSkeletonOwner();

            return owner != null
                    && owner.isAlive()
                    && owner.getTarget() == null
                    && this.wolf.distanceToSqr(owner) <= 100.0;
        }

        @Override
        public void start() {
            this.wolf.setOrderedToSit(true);
            this.wolf.getNavigation().stop();
        }

        @Override
        public void tick() {
            this.wolf.getNavigation().stop();
        }
    }

    // Targets the same enemies as the skeleton owner.
    static class SkeletonTargetGoal extends TargetGoal {

        private final SkeletonWolfEntity wolf;

        public SkeletonTargetGoal(SkeletonWolfEntity wolf) {
            super(wolf, false);
            this.wolf = wolf;
        }

        @Override
        public boolean canUse() {
            Skeleton owner = this.wolf.getSkeletonOwner();

            if (owner != null && owner.isAlive() && owner.getTarget() != null) {
                this.targetMob = owner.getTarget();
                return true;
            }

            return false;
        }

        @Override
        public void start() {
            this.wolf.setOrderedToSit(false);
            this.mob.setTarget(this.targetMob);
            super.start();
        }
    }
}

