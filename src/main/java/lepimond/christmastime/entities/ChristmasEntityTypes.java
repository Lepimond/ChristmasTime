package lepimond.christmastime.entities;

import lepimond.christmastime.ChristmasTime;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ChristmasEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ChristmasTime.MODID);

    public static final RegistryObject<EntityType<LeggedBoat>> leggedBoat = ENTITY_TYPES.register("legged_boat",
            () -> EntityType.Builder.<LeggedBoat>of(LeggedBoat::new, MobCategory.MISC)
                    .sized(0.95F, 2.45F)
                    .setCustomClientFactory(LeggedBoat::new)
                    .build(new ResourceLocation(ChristmasTime.MODID, "legged_boat").toString()));

    //1.0f, 3.0f legged boat size
    //Whether the boat is underwater is being checked through absolute values, depends on hitbox. Unfortunately.
}
