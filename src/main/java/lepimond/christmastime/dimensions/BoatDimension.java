package lepimond.christmastime.dimensions;

import lepimond.christmastime.registry.ChristmasDimensions;
import net.minecraft.core.Registry;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BoatDimension {
    public static DimensionType boatType;
    public static ServerLevel boatDimension;

    @SubscribeEvent
    public static void onServerStarted(ServerStartedEvent event) {
        boatType = event.getServer().registryAccess().registryOrThrow(Registry.DIMENSION_TYPE_REGISTRY).get(ChristmasDimensions.boatTypeKey);
        boatDimension = event.getServer().getLevel(ChristmasDimensions.boatDimensionKey);
    }
}
