package lepimond.christmastime.registry;

import lepimond.christmastime.ChristmasTime;
import lepimond.christmastime.blocks.ChristmasLeaves;
import lepimond.christmastime.blocks.ChristmasPresent;
import lepimond.christmastime.blocks.CookieCrop;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ChristmasBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ChristmasTime.MODID);

    public static final RegistryObject<Block> christmasLeaves = register("christmas_leaves", ChristmasLeaves::new);

    public static final RegistryObject<Block> christmasPresent = register("christmas_present", ChristmasPresent::new);

    public static final RegistryObject<Block> cookieCrop = register("cookie_crop", CookieCrop::new);

    private static RegistryObject<Block> register(String name, Supplier<? extends Block> supplier) {
        RegistryObject<Block> result = BLOCKS.register(name, supplier);
        ChristmasItems.ITEMS.register(name, () -> new BlockItem(result.get(), new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE)));

        return result;
    }
}
