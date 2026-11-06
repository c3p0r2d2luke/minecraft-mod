package net.ipv64.frenzi4309.client.renderer.entity;

import net.ipv64.frenzi4309.client.model.entity.SkeletonWolf;
import net.ipv64.frenzi4309.entity.SkeletonWolfEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

@SuppressWarnings({"null"})

public class SkeletonWolfRenderer extends MobRenderer<SkeletonWolfEntity, LivingEntityRenderState, SkeletonWolf> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(
            "hackcraft", "textures/entity/skeleton_wolf.png");

    public SkeletonWolfRenderer(EntityRendererProvider.Context context) {
        super(context, new SkeletonWolf(context.bakeLayer(SkeletonWolf.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }

    @Override
    public Identifier getTextureLocation(LivingEntityRenderState state) {
        return TEXTURE;
    }
}
