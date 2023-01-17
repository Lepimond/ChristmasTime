package lepimond.christmastime.worldgen;

import lepimond.christmastime.ChristmasTime;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

import java.util.ArrayList;

public class ChristmasBiomeKeys {
    public static final ArrayList<ResourceKey<Biome>> KEYS = new ArrayList<ResourceKey<Biome>>();

    public static final ResourceKey<Biome> boatBiome = registerBiome("boat_biome");

    private static ResourceKey<Biome> registerBiome(String name) {
        ResourceKey<Biome> key = ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(ChristmasTime.MODID, name));
        KEYS.add(key);
        return key;
    }
}
