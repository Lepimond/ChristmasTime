package lepimond.christmastime;

import com.mojang.logging.LogUtils;
import lepimond.christmastime.registry.ChristmasBlocks;
import lepimond.christmastime.registry.ChristmasItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
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

        MinecraftForge.EVENT_BUS.register(this);

        LOGGER.debug("Christmas Time welcomes you!");
    }
}
