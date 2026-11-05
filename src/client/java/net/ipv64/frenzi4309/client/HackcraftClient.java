package net.ipv64.frenzi4309.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.ipv64.frenzi4309.client.model.entity.SkeletonWolf;
import net.ipv64.frenzi4309.client.renderer.entity.SkeletonWolfRenderer;
import net.ipv64.frenzi4309.entity.SkeletonWolfEntity;

public class HackcraftClient implements ClientModInitializer {

    @Override
    @SuppressWarnings({"null", "deprecation"})
    public void onInitializeClient() {
        ModelLayerRegistry.registerModelLayer(SkeletonWolf.LAYER_LOCATION, SkeletonWolf::createBodyLayer);
        EntityRendererRegistry.register(SkeletonWolfEntity.TYPE, SkeletonWolfRenderer::new);
    }
}