package lepimond.christmastime.registry;

import lepimond.christmastime.ChristmasTime;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ChristmasParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, ChristmasTime.MODID);

    public static final RegistryObject<SimpleParticleType> boatParticle = PARTICLE_TYPES.register("boat_particle", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> monsterParticle = PARTICLE_TYPES.register("monster_particle", () -> new SimpleParticleType(true));
}
