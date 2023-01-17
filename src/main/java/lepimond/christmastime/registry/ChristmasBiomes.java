package lepimond.christmastime.registry;

import lepimond.christmastime.ChristmasTime;
import lepimond.christmastime.worldgen.ChristmasBiomeKeys;
import lepimond.christmastime.worldgen.biomes.BoatBiome;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(modid = ChristmasTime.MODID, bus= Mod.EventBusSubscriber.Bus.MOD)
public class ChristmasBiomes {
    @SubscribeEvent
    public static void onRegisterEvent(RegisterEvent event) {
        event.register(ForgeRegistries.Keys.BIOMES, helper -> {
            helper.register(ChristmasBiomeKeys.boatBiome.location().getPath(), BoatBiome.create());
        });
    }
}
