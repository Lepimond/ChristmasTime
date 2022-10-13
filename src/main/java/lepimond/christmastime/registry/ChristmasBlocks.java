package lepimond.christmastime.registry;

import lepimond.christmastime.ChristmasTime;
import lepimond.christmastime.blocks.ChristmasLeaves;
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

    private static RegistryObject<Block> register(String name, Supplier<? extends Block> supplier) {
        RegistryObject<Block> result = BLOCKS.register(name, supplier);
        ChristmasItems.ITEMS.register("christmas_leaves", () -> new BlockItem(result.get(), new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE)));

        return result;
    }
}
