package lepimond.christmastime.entities.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import lepimond.christmastime.ChristmasTime;
import lepimond.christmastime.entities.BlinkEffect;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class BlinkEffectModel<T extends BlinkEffect> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(ChristmasTime.MODID, "blink_effect"), "main");
	private final ModelPart roof;
	private final ModelPart foundation;
	private final ModelPart bone;

	public BlinkEffectModel(ModelPart root) {
		this.roof = root.getChild("roof");
		this.foundation = root.getChild("foundation");
		this.bone = root.getChild("bone");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition roof = partdefinition.addOrReplaceChild("roof", CubeListBuilder.create().texOffs(0, 18).addBox(6.0F, -2.0F, -8.0F, 2.0F, 2.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-8.0F, -2.0F, -8.0F, 2.0F, 2.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 7.0F, 0.0F));

		PartDefinition cube_r1 = roof.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 36).addBox(-8.0F, -2.0F, -6.0F, 2.0F, 2.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(16, 38).addBox(6.0F, -2.0F, -6.0F, 2.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition foundation = partdefinition.addOrReplaceChild("foundation", CubeListBuilder.create().texOffs(20, 20).addBox(6.0F, -2.0F, -8.0F, 2.0F, 2.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(20, 2).addBox(-8.0F, -2.0F, -8.0F, 2.0F, 2.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r2 = foundation.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(40, 0).addBox(-8.0F, -2.0F, -6.0F, 2.0F, 2.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(40, 20).addBox(6.0F, -2.0F, -6.0F, 2.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(44, 38).addBox(-1.0F, -19.0F, -1.0F, 2.0F, 19.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		roof.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		foundation.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}