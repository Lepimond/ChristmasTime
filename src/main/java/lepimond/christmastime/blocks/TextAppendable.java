package lepimond.christmastime.blocks;

import net.minecraft.network.chat.Component;

/**
 * Marks blocks the block-items of which can be appended with a tip
 * */
public interface TextAppendable {
    Component getHoverText();
}
