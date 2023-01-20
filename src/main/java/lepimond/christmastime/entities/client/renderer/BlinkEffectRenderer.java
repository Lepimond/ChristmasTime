package lepimond.christmastime.entities.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import lepimond.christmastime.ChristmasTime;
import lepimond.christmastime.entities.BlinkEffect;
import lepimond.christmastime.entities.client.model.BlinkEffectModel;
import net.minecraft.client.model.MinecartModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class BlinkEffectRenderer extends EntityRenderer<BlinkEffect> {
    protected final BlinkEffectModel<BlinkEffect> model;

    public BlinkEffectRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new BlinkEffectModel<>(context.bakeLayer(BlinkEffectModel.LAYER_LOCATION));
    }

    public void render(BlinkEffect entity, float p_115419_, float p_115420_, PoseStack p_115421_, MultiBufferSource p_115422_, int p_115423_) {
        super.render(entity, p_115419_, p_115420_, p_115421_, p_115422_, p_115423_);
        this.model.setupAnim(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        VertexConsumer vertexconsumer = p_115422_.getBuffer(this.model.renderType(this.getTextureLocation(entity)));
        this.model.renderToBuffer(p_115421_, vertexconsumer, p_115423_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(BlinkEffect effect) {
        return new ResourceLocation(ChristmasTime.MODID, "textures/entity/blink_effect.png");
    }
}
