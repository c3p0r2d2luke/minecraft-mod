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
        // Follow skeleton owner when nearby
        this.goalSelector.addGoal(6, new FollowSkeletonGoal(this, 1.25));
        // Target whatever the skeleton attacks or targets
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
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, id);
        
        EntityType<SkeletonWolfEntity> type = EntityType.Builder
                .of(SkeletonWolfEntity::new, MobCategory.MONSTER)
                .sized(0.6F, 0.85F)
                .clientTrackingRange(10)
                .build(key);
                
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, id, type);
    }

    // AI Goal: Navigation to follow Skeleton
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
            return owner != null && owner.isAlive() && this.wolf.distanceToSqr(owner) > 12.0;
        }

        @Override
        public void tick() {
            Skeleton owner = this.wolf.getSkeletonOwner();
            if (owner != null) {
                this.wolf.getLookControl().setLookAt(owner, 10.0F, (float) this.wolf.getMaxHeadXRot());
                this.wolf.getNavigation().moveTo(owner, this.speed);
            }
        }
    }

    // AI Goal: Targeting Skeleton's target
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
            this.mob.setTarget(this.targetMob);
            super.start();
        }
    }
}