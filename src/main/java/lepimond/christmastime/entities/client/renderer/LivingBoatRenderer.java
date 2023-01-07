package lepimond.christmastime.entities.client.renderer;

import lepimond.christmastime.ChristmasTime;
import lepimond.christmastime.entities.LeggedBoat;
import lepimond.christmastime.entities.LivingBoat;
import lepimond.christmastime.entities.client.model.LeggedBoatModel;
import lepimond.christmastime.entities.client.model.LivingBoatModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class LivingBoatRenderer extends MobRenderer<LivingBoat, LivingBoatModel> {

    public LivingBoatRenderer(EntityRendererProvider.Context context) {
        super(context, new LivingBoatModel(context.bakeLayer(LivingBoatModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(LivingBoat boat) {
        return new ResourceLocation(ChristmasTime.MODID, "textures/entity/legged_boat.png");
    }
}
