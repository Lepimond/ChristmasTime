package lepimond.christmastime.registry;

import lepimond.christmastime.ChristmasTime;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ChristmasSounds {
    public static final DeferredRegister SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ChristmasTime.MODID);

    public static final RegistryObject<SoundEvent> christmasSong =
            SOUNDS.register("music_disc.christmas_song", () -> new SoundEvent(new ResourceLocation(ChristmasTime.MODID, "christmas_song")));

}
