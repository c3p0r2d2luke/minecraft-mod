package net.ipv64.frenzi4309.client.model.entity;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public class SkeletonWolf extends EntityModel<LivingEntityRenderState> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			Identifier.fromNamespaceAndPath("hackcraft", "skeleton_wolf"), "main");
	private final ModelPart head;
	private final ModelPart mane;
	private final ModelPart body;
	private final ModelPart leg1;
	private final ModelPart leg2;
	private final ModelPart leg3;
	private final ModelPart leg4;
	private final ModelPart tail;

	public SkeletonWolf(ModelPart root) {
		super(root);
		this.head = root.getChild("head");
		this.mane = root.getChild("mane");
		this.body = root.getChild("body");
		this.leg1 = root.getChild("leg1");
		this.leg2 = root.getChild("leg2");
		this.leg3 = root.getChild("leg3");
		this.leg4 = root.getChild("leg4");
		this.tail = root.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -3.0F, -2.0F, 6.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(27, 31).addBox(1.5F, 1.98F, -5.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 4).addBox(1.5F, 1.98F, -5.0F, 0.0F, 0.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(10, 2).addBox(2.5F, 0.98F, -3.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(27, 31).addBox(1.5F, -0.02F, -5.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(1, 34).addBox(0.5F, 1.98F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(5, 1).addBox(1.5F, 1.98F, -2.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(8, 34).addBox(0.5F, 1.98F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.5F, -0.02F, -5.0F, 0.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(28, 32).addBox(-0.5F, 1.98F, -5.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(6, 2).addBox(-0.5F, 0.98F, -3.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(28, 33).addBox(-0.5F, -0.02F, -5.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(8, 31).mirror().addBox(0.5F, 1.98F, -4.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(3, 2).addBox(1.5F, -0.02F, -4.0F, 0.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(3, 2).addBox(0.5F, -0.02F, -4.0F, 1.0F, 0.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(3, 2).addBox(0.5F, -0.02F, -4.0F, 0.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(3, 2).addBox(0.5F, 0.98F, -4.0F, 1.0F, 0.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(1, 0).addBox(1.5F, 0.98F, -4.0F, 0.0F, 0.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(1, 0).addBox(0.5F, 0.98F, -2.0F, 1.0F, 0.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(1, 0).addBox(0.5F, 0.98F, -4.0F, 0.0F, 0.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(3, 2).addBox(0.5F, 0.98F, -4.0F, 1.0F, 0.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(1, 0).addBox(0.5F, -0.02F, -4.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 13.5F, -7.0F));

		PartDefinition mane = partdefinition.addOrReplaceChild("mane", CubeListBuilder.create().texOffs(20, 26).addBox(4.0F, 2.0F, -3.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(20, 24).mirror().addBox(2.0F, 2.0F, -3.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(21, 29).mirror().addBox(2.0F, 2.0F, 3.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(21, 2).addBox(2.0F, 0.0F, -3.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(20, 26).addBox(4.0F, 0.0F, -2.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(23, 33).addBox(2.0F, 0.0F, 3.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(24, 19).addBox(2.0F, -2.0F, -3.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(20, 27).addBox(4.0F, -2.0F, -2.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(28, 33).addBox(2.0F, -2.0F, 3.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(24, 6).addBox(0.0F, -3.0F, 3.0F, 2.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(30, 32).addBox(-3.0F, 2.0F, -3.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(33, 12).addBox(-2.0F, 2.0F, 3.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(24, 16).addBox(-3.0F, 2.0F, -2.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 11).addBox(-3.0F, 0.0F, -3.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(1, 33).addBox(-2.0F, 0.0F, 3.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(24, 11).addBox(-3.0F, 0.0F, -2.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(3, 16).addBox(-3.0F, -2.0F, -3.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(4, 33).addBox(-2.0F, -2.0F, 3.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(26, 32).addBox(-3.0F, -2.0F, -2.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 14.0F, -3.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(3.0F, 5.0F, -2.0F, 0.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(26, 31).addBox(2.0F, 5.0F, -2.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(34, 35).addBox(1.0F, 5.0F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(10, 1).addBox(1.0F, 3.0F, 2.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(11, 1).addBox(1.0F, 1.0F, 2.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(12, 27).addBox(1.0F, -1.0F, -3.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(26, 31).addBox(2.0F, -1.0F, -2.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(34, 35).addBox(1.0F, -1.0F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(16, 28).addBox(-1.0F, -2.0F, 2.0F, 2.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(34, 35).addBox(-2.0F, 5.0F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.0F, 5.0F, -2.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(26, 31).addBox(-3.0F, 5.0F, -2.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(22, 1).addBox(-1.0F, 3.0F, 2.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(23, 1).addBox(-1.0F, 1.0F, 2.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(24, 31).addBox(-3.0F, -1.0F, -3.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(34, 35).addBox(-2.0F, -1.0F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(27, 32).addBox(-3.0F, -1.0F, -2.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(20, 2).addBox(-1.0F, -2.0F, 2.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(20, 0).addBox(-2.0F, 5.0F, -2.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 14.0F, 2.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition leg1 = partdefinition.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(0, 10).addBox(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, 16.0F, 7.0F));

		PartDefinition leg2 = partdefinition.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(8, 10).addBox(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 16.0F, 7.0F));

		PartDefinition leg3 = partdefinition.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(16, 10).addBox(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, 16.0F, -4.0F));

		PartDefinition leg4 = partdefinition.addOrReplaceChild("leg4", CubeListBuilder.create().texOffs(0, 20).addBox(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 16.0F, -4.0F));

		PartDefinition tail = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(34, 35).addBox(0.0F, 7.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(34, 35).addBox(1.0F, 6.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(1, 3).addBox(0.0F, 5.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(4, 6).addBox(1.0F, 4.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(7, 9).addBox(0.0F, 3.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(10, 12).addBox(1.0F, 2.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(13, 15).addBox(0.0F, 1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(16, 18).addBox(1.0F, 0.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(19, 21).addBox(0.0F, 0.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(22, 24).addBox(1.0F, 7.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(25, 27).addBox(1.0F, 5.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(28, 30).addBox(1.0F, 3.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(31, 33).addBox(0.0F, 6.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(34, 37).addBox(0.0F, 4.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(36, 37).addBox(0.0F, 2.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(36, 37).addBox(1.0F, 1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 12.0F, 10.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(LivingEntityRenderState state) {
		float headRot = (float) (Math.PI / 180.0);
		head.xRot = state.xRot * headRot;
		head.yRot = (state.yRot - state.bodyRot) * headRot;
		float walk = state.walkAnimationPos * 0.6662F;
		float swing = 1.4F * state.walkAnimationSpeed;
		leg1.xRot = (float) Math.cos(walk) * swing;
		leg2.xRot = (float) Math.cos(walk + Math.PI) * swing;
		leg3.xRot = (float) Math.cos(walk + Math.PI) * swing;
		leg4.xRot = (float) Math.cos(walk) * swing;
		tail.yRot = (float) Math.sin(state.ageInTicks * 0.2F) * 0.15F;
	}
}