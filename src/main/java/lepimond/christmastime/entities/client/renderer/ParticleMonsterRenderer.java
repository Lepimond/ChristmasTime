package lepimond.christmastime.entities.client.renderer;

import lepimond.christmastime.ChristmasTime;
import lepimond.christmastime.entities.ParticleMonster;
import lepimond.christmastime.entities.client.model.LivingBoatModel;
import lepimond.christmastime.entities.client.model.ParticleMonsterModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ParticleMonsterRenderer extends MobRenderer<ParticleMonster, ParticleMonsterModel> {
    public ParticleMonsterRenderer(EntityRendererProvider.Context context) {
        super(context, new ParticleMonsterModel(context.bakeLayer(ParticleMonsterModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(ParticleMonster monster) {
        return new ResourceLocation(ChristmasTime.MODID, "textures/entity/particle_monster.png");
    }
}
