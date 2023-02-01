package lepimond.christmastime.registry;

import lepimond.christmastime.ChristmasTime;
import lepimond.christmastime.entities.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ChristmasEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ChristmasTime.MODID);

    //Whether the boat is underwater is being checked through absolute values, depends on hitbox
    public static final RegistryObject<EntityType<LeggedBoat>> leggedBoat = ENTITY_TYPES.register("legged_boat",
            () -> EntityType.Builder.<LeggedBoat>of(LeggedBoat::new, MobCategory.MISC)
                    .sized(0.95F, 2.45F)
                    .setCustomClientFactory(LeggedBoat::new)
                    .build(new ResourceLocation(ChristmasTime.MODID, "legged_boat").toString()));

    public static final RegistryObject<EntityType<GunSnowball>> gunSnowball = ENTITY_TYPES.register("gun_snowball",
            () -> EntityType.Builder.<GunSnowball>of(GunSnowball::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build(new ResourceLocation(ChristmasTime.MODID, "gun_snowball").toString()));

    public static final RegistryObject<EntityType<LivingBoat>> livingBoat = ENTITY_TYPES.register("living_boat",
            () -> EntityType.Builder.<LivingBoat>of(LivingBoat::new, MobCategory.CREATURE)
                    .sized(0.95F, 2.45F)
                    .build(new ResourceLocation(ChristmasTime.MODID, "living_boat").toString()));

    public static final RegistryObject<EntityType<BlinkEffect>> blinkEffect = ENTITY_TYPES.register("blink_effect",
            () -> EntityType.Builder.<BlinkEffect>of(BlinkEffect::new, MobCategory.MISC)
                    .sized(0.95F, 2.45F)
                    .build(new ResourceLocation(ChristmasTime.MODID, "blink_effect").toString()));

    public static final RegistryObject<EntityType<ParticleMonster>> particleMonster = ENTITY_TYPES.register("particle_monster",
            () -> EntityType.Builder.<ParticleMonster>of(ParticleMonster::new, MobCategory.CREATURE)
                    .sized(0.95F, 2.45F)
                    .build(new ResourceLocation(ChristmasTime.MODID, "particle_monster").toString()));
}
