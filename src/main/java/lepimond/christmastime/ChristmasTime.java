package lepimond.christmastime;

import com.mojang.logging.LogUtils;
import lepimond.christmastime.registry.ChristmasEntities;
import lepimond.christmastime.entities.client.model.LeggedBoatModel;
import lepimond.christmastime.entities.client.renderer.LeggedBoatRenderer;
import lepimond.christmastime.registry.ChristmasBlocks;
import lepimond.christmastime.registry.ChristmasItems;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(ChristmasTime.MODID)
public class ChristmasTime {
    public static final String MODID = "christmastime";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ChristmasTime() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ChristmasItems.ITEMS.register(eventBus);
        ChristmasBlocks.BLOCKS.register(eventBus);
        ChristmasEntities.ENTITY_TYPES.register(eventBus);

        MinecraftForge.EVENT_BUS.register(this);

        LOGGER.debug("Christmas Time welcomes you!");
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            //Without that, the crop just renders as completely black
            ItemBlockRenderTypes.setRenderLayer(ChristmasBlocks.cookieCrop.get(), RenderType.cutout());
        }

        //Registers which Renderer classes belong to which entities
        @SubscribeEvent
        public static void onEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(ChristmasEntities.leggedBoat.get(), LeggedBoatRenderer::new);
            event.registerEntityRenderer(ChristmasEntities.gunSnowball.get(), ThrownItemRenderer::new);
        }

        @SubscribeEvent
        public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(LeggedBoatModel.LAYER_LOCATION, LeggedBoatModel::createBodyModel);
        }
    }
}