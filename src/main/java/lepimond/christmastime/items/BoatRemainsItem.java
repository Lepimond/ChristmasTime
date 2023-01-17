package lepimond.christmastime.items;

import lepimond.christmastime.registry.ChristmasItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class BoatRemainsItem extends Item {
    public BoatRemainsItem() {
        super(ChristmasItems.standardProps());
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        components.add(Component.literal("Drops from Living Boats").withStyle(ChatFormatting.AQUA));
    }
}
