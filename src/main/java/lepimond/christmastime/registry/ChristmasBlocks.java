package lepimond.christmastime.registry;

import lepimond.christmastime.ChristmasTime;
import lepimond.christmastime.blocks.ChristmasLeaves;
import lepimond.christmastime.blocks.ChristmasPresent;
import lepimond.christmastime.blocks.CookieCrop;
import lepimond.christmastime.blocks.TextAppendable;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

public class ChristmasBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ChristmasTime.MODID);

    public static final RegistryObject<Block> christmasLeaves = register("christmas_leaves", ChristmasLeaves::new);

    public static final RegistryObject<Block> christmasPresent = register("christmas_present", ChristmasPresent::new);

    public static final RegistryObject<Block> cookieCrop = register("cookie_crop", CookieCrop::new);

    private static RegistryObject<Block> register(String name, Supplier<? extends Block> supplier) {
        RegistryObject<Block> result = BLOCKS.register(name, supplier);
        ChristmasItems.ITEMS.register(name, () -> new BlockItem(result.get(), new Item.Properties().tab(ChristmasCreativeTabs.CHRISTMAS_TAB)) {
            @Override
            public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
                components.add(((TextAppendable) supplier.get()).getHoverText());
            }
        });

        return result;
    }
}

