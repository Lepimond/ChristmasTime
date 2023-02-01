package lepimond.christmastime.entities.client.model;

import com.google.common.collect.ImmutableList;
import lepimond.christmastime.ChristmasTime;
import lepimond.christmastime.entities.ParticleMonster;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class ParticleMonsterModel extends ListModel<ParticleMonster> {
    private final ImmutableList<ModelPart> parts;

    public ParticleMonsterModel(ModelPart root) {
        ImmutableList.Builder<ModelPart> builder = new ImmutableList.Builder<>();
        this.parts = builder.build();
    }

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(ChristmasTime.MODID, "particle_monster"), "main");

    @Override
    public Iterable<ModelPart> parts() {
        return this.parts;
    }

    public static LayerDefinition createBodyModel() {
        MeshDefinition meshdefinition = new MeshDefinition();
        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(ParticleMonster p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {}
}
