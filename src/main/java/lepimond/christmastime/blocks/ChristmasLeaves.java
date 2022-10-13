package lepimond.christmastime.blocks;

import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class ChristmasLeaves extends LeavesBlock {
    public ChristmasLeaves() {
        super(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion());
    }

    @Override
    protected boolean decaying(BlockState blockState) {
        return false;
    }
}
