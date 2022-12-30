package lepimond.christmastime.entities.client.model;

import com.google.common.collect.ImmutableList;
import com.ibm.icu.text.Normalizer;
import lepimond.christmastime.ChristmasTime;
import lepimond.christmastime.entities.LeggedBoat;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.vehicle.Boat;

public class LeggedBoatModel extends ListModel<LeggedBoat> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(ChristmasTime.MODID, "legged_boat"), "main");

    private final ModelPart leftPaddle;
    private final ModelPart rightPaddle;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;

    private final ImmutableList<ModelPart> parts;

    public LeggedBoatModel(ModelPart root) {
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
        float yOffset = -3.0F;
        float zOffset = 0.0F;
        float xRotation = 0.0F;
        float yRotation = -1.5708F;
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

    public void setupAnim(LeggedBoat boat, float p_102270_, float p_102271_, float p_102272_, float p_102273_, float p_102274_) {
        animateLimb(boat, 3, this.leftPaddle);
        animateLimb(boat, 2, this.rightPaddle);

        animateLimb(boat, 1, this.rightLeg);
        animateLimb(boat, 0, this.leftLeg);
    }

    public ImmutableList<ModelPart> parts() {
        return this.parts;
    }

    private static void animateLimb(Boat boat, int whichLimb, ModelPart paddle) {
        float f = boat.getPaddleState(whichLimb) ? Mth.sin(Math.abs(System.currentTimeMillis() % 1000 / 1000.0F - 0.5F)) + 0.25F : 0.5F;
        //Lerp(a, b, t) returns a if t == 0, b if t == 1, and (a + b) / 2 if t == 0.5
        paddle.zRot = Mth.clampedLerp(- Mth.PI / 2, Mth.PI / 2, f);

        //Separate anim for drowning
        if (boat.isInWater() && !boat.isOnGround() && whichLimb > 1) {
            f = Mth.sin(Math.abs(((long)(System.currentTimeMillis() * 1.5D)) % 1000 / 1000.0F - 0.5F)) + 0.25F;
            paddle.zRot = Mth.clampedLerp(Mth.PI / 2, 3 * Mth.PI / 2, f);
        }

        //Only right limbs (right are odd, left are even)
        if (whichLimb % 2 == 1) {
            paddle.zRot = - paddle.zRot;
        }
    }
}
