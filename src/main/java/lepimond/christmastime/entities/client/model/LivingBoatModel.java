package lepimond.christmastime.entities.client.model;

import com.google.common.collect.ImmutableList;
import com.ibm.icu.text.Normalizer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import lepimond.christmastime.ChristmasTime;
import lepimond.christmastime.entities.LeggedBoat;
import lepimond.christmastime.entities.LivingBoat;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.vehicle.Boat;

public class LivingBoatModel extends ListModel<LivingBoat> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(ChristmasTime.MODID, "living_boat"), "main");

    private final ModelPart leftPaddle;
    private final ModelPart rightPaddle;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;

    private final ImmutableList<ModelPart> parts;

    public LivingBoatModel(ModelPart root) {
        this.leftPaddle = root.getChild("left_paddle");
        this.rightPaddle = root.getChild("right_paddle");

        this.leftLeg = root.getChild("left_leg");
        this.rightLeg = root.getChild("right_leg");

        ImmutableList.Builder<ModelPart> builder = new ImmutableList.Builder<>();
        builder.add(root.getChild("bottom"), root.getChild("back"), root.getChild("front"), root.getChild("right"), root.getChild("left"), root.getChild("right_leg"), root.getChild("left_leg"), this.leftPaddle, this.rightPaddle);

        this.parts = builder.build();
    }

    public static LayerDefinition createBodyModel() {
        float xOffset = 0.0F;
        float yOffset = 15.0F;
        float zOffset = 0.0F;
        float xRotation = 0.0F;
        float yRotation = 0.0F;
        float zRotation = 0.0F;

        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -32.0F, 4.0F, 16.0F, 28.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(xOffset, yOffset, zOffset, xRotation, yRotation, zRotation));
        partdefinition.addOrReplaceChild("back", CubeListBuilder.create().texOffs(0, 32).addBox(-9.0F, -4.0F, -2.0F, 18.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(xOffset, yOffset, zOffset, xRotation, yRotation, zRotation));
        partdefinition.addOrReplaceChild("front", CubeListBuilder.create().texOffs(0, 40).addBox(-8.0F, -34.0F, -2.0F, 16.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(xOffset, yOffset, zOffset, xRotation, yRotation, zRotation));
        partdefinition.addOrReplaceChild("left", CubeListBuilder.create().texOffs(38, 42).addBox(8.0F, -32.0F, -2.0F, 3.0F, 28.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(xOffset, yOffset, zOffset, xRotation, yRotation, zRotation));
        partdefinition.addOrReplaceChild("right", CubeListBuilder.create().texOffs(42, 0).addBox(-11.0F, -32.0F, -2.0F, 3.0F, 28.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(xOffset, yOffset, zOffset, xRotation, yRotation, zRotation));
        partdefinition.addOrReplaceChild("right_paddle", CubeListBuilder.create().texOffs(56, 34).addBox(-13.0F, -2.0F, -1.0F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(50, 41).addBox(-12.0F, 11.0F, -1.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(14, 48).addBox(-13.0F, 11.0F, -3.0F, 1.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F + xOffset, -24.0F + yOffset, 0.0F + zOffset, xRotation, yRotation, zRotation));
        partdefinition.addOrReplaceChild("left_paddle", CubeListBuilder.create().texOffs(28, 48).addBox(11.0F, -2.0F, -1.0F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(48, 34).addBox(11.0F, 11.0F, -1.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 48).addBox(12.0F, 11.0F, -3.0F, 1.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F + xOffset, -24.0F + yOffset, 0.0F + zOffset, xRotation, yRotation, zRotation));

        partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(60, 0).addBox(-6.0F, -1.0F, 0.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F + xOffset, -1.0F + yOffset, 0.0F + zOffset, xRotation, yRotation, zRotation));
        partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(56, 49).addBox(4.0F, -1.0F, 0.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F + xOffset, -1.0F + yOffset, 0.0F + zOffset, xRotation, yRotation, zRotation));


        //partdefinition.addOrReplaceChild("water_patch", CubeListBuilder.create().texOffs(0, 0).addBox(-14.0F, -9.0F, -3.0F, 28.0F, 16.0F, 3.0F), PartPose.offsetAndRotation(0.0F, -3.0F, 1.0F, ((float)Math.PI / 2F), 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    public void setupAnim(LivingBoat boat, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        leftPaddle.xRot = - Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        rightPaddle.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        leftLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        rightLeg.xRot = - Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }

    public ImmutableList<ModelPart> parts() {
        return this.parts;
    }
}
