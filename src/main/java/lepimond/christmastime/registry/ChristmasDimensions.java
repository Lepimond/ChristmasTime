package lepimond.christmastime.registry;

import lepimond.christmastime.ChristmasTime;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

public class ChristmasDimensions {
    private static final ResourceLocation boatWorldName = new ResourceLocation(ChristmasTime.MODID, "boat_world");

    public static final ResourceKey<Level> boatDimensionKey = ResourceKey.create(Registry.DIMENSION_REGISTRY, boatWorldName);
    public static final ResourceKey<DimensionType> boatTypeKey = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, boatWorldName);
}
