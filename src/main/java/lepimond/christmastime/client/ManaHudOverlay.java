package lepimond.christmastime.client;

import com.mojang.blaze3d.systems.RenderSystem;
import lepimond.christmastime.ChristmasTime;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class ManaHudOverlay {
    public static final IGuiOverlay HUD_MANA = ((gui, poseStack, partialTick, screenWidth, screenHeight) -> {
        int x = 20;
        int y = 20;
        int width = screenWidth / 10;
        int height = width;

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0 , new ResourceLocation(ChristmasTime.MODID, "textures/gui/mana_" + ClientManaData.getMana() + ".png"));
        GuiComponent.blit(poseStack, x, y, 0, 0, width, height, width, height);
    });
}
