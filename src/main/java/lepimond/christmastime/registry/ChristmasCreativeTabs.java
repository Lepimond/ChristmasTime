package lepimond.christmastime.registry;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ChristmasCreativeTabs {
    public static final CreativeModeTab CHRISTMAS_TAB = new CreativeModeTab("christmas_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ChristmasBlocks.christmasPresent.get());
        }
    };
}
