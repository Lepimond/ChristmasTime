package lepimond.christmastime;

import com.mojang.logging.LogUtils;
import lepimond.christmastime.capability.PlayerMana;
import lepimond.christmastime.capability.PlayerManaProvider;
import lepimond.christmastime.client.ManaHudOverlay;
import lepimond.christmastime.entities.LivingBoat;
import lepimond.christmastime.entities.ParticleMonster;
import lepimond.christmastime.entities.client.model.BlinkEffectModel;
import lepimond.christmastime.entities.client.model.LivingBoatModel;
import lepimond.christmastime.entities.client.model.ParticleMonsterModel;
import lepimond.christmastime.entities.client.renderer.BlinkEffectRenderer;
import lepimond.christmastime.entities.client.renderer.LivingBoatRenderer;
import lepimond.christmastime.entities.client.renderer.ParticleMonsterRenderer;
import lepimond.christmastime.particles.BoatPortalParticle;
import lepimond.christmastime.particles.MonsterParticle;
import lepimond.christmastime.registry.*;
import lepimond.christmastime.entities.client.model.LeggedBoatModel;
import lepimond.christmastime.entities.client.renderer.LeggedBoatRenderer;
import lepimond.christmastime.worldgen.ChristmasBiomeKeys;
import lepimond.christmastime.worldgen.biomes.BoatBiome;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.portal.PortalInfo;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.EntityTravelToDimensionEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
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
        ChristmasSounds.SOUNDS.register(eventBus);
        ChristmasParticles.PARTICLE_TYPES.register(eventBus);
        ChristmasBlockEntities.BLOCK_ENTITY_TYPES.register(eventBus);
        ChristmasMessages.register();

        MinecraftForge.EVENT_BUS.register(this);

        LOGGER.debug("Christmas Time welcomes you!");
    }

    @Mod.EventBusSubscriber(modid = MODID)
    public static class CommonModEvents {
        @SubscribeEvent
        public static void onCommonSetup(FMLCommonSetupEvent event) {
            event.enqueueWork(() -> {
                SpawnPlacements.register(ChristmasEntities.livingBoat.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE, LivingBoat::canSpawn);
                SpawnPlacements.register(ChristmasEntities.particleMonster.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
                BiomeManager.addAdditionalOverworldBiomes(ChristmasBiomeKeys.boatBiome);
            });
        }

        @SubscribeEvent
        public static void entityAttributes(EntityAttributeCreationEvent event) {
            event.put(ChristmasEntities.livingBoat.get(), LivingBoat.getExampleAttributes().build());
            event.put(ChristmasEntities.particleMonster.get(), Zombie.createAttributes().build());
        }

        @SubscribeEvent
        public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
            if (event.getObject() instanceof Player) {
                if (!event.getObject().getCapability(PlayerManaProvider.PLAYER_MANA).isPresent()) {
                    event.addCapability(new ResourceLocation(ChristmasTime.MODID, "properties"), new PlayerManaProvider());
                }
            }
        }

        @SubscribeEvent
        public static void onPlayerCloned(PlayerEvent.Clone event) {
            if (event.isWasDeath()) {
                event.getOriginal().getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(oldStore -> {
                    event.getOriginal().getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(newStore -> {
                        newStore.copyFrom(oldStore);
                    });
                });
            }
        }

        @SubscribeEvent
        public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
            event.register(PlayerMana.class);
        }
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            //Without that, the crop just renders as completely black
            ItemBlockRenderTypes.setRenderLayer(ChristmasBlocks.cookieCrop.get(), RenderType.cutout());
        }

        @SubscribeEvent
        public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
            Minecraft.getInstance().particleEngine.register(ChristmasParticles.boatParticle.get(),
                    BoatPortalParticle.BoatPortalProvider::new);
            Minecraft.getInstance().particleEngine.register(ChristmasParticles.monsterParticle.get(),
                    MonsterParticle.MonsterParticleProvider::new);
        }

        //Registers which Renderer classes belong to which entities
        @SubscribeEvent
        public static void onEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(ChristmasEntities.leggedBoat.get(), LeggedBoatRenderer::new);
            event.registerEntityRenderer(ChristmasEntities.livingBoat.get(), LivingBoatRenderer::new);
            event.registerEntityRenderer(ChristmasEntities.gunSnowball.get(), ThrownItemRenderer::new);
            event.registerEntityRenderer(ChristmasEntities.blinkEffect.get(), BlinkEffectRenderer::new);
            event.registerEntityRenderer(ChristmasEntities.particleMonster.get(), ParticleMonsterRenderer::new);
        }

        @SubscribeEvent
        public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(LeggedBoatModel.LAYER_LOCATION, LeggedBoatModel::createBodyModel);
            event.registerLayerDefinition(LivingBoatModel.LAYER_LOCATION, LivingBoatModel::createBodyModel);
            event.registerLayerDefinition(BlinkEffectModel.LAYER_LOCATION, BlinkEffectModel::createBodyLayer);
            event.registerLayerDefinition(ParticleMonsterModel.LAYER_LOCATION, ParticleMonsterModel::createBodyModel);
        }

        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
            event.registerAboveAll("mana", ManaHudOverlay.HUD_MANA);
        }
    }
}