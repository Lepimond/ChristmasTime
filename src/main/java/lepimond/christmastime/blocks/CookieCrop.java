package lepimond.christmastime.blocks;

import lepimond.christmastime.registry.ChristmasItems;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class CookieCrop extends CropBlock {

    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 7);

    public CookieCrop() {
        super(BlockBehaviour.Properties.copy(Blocks.WHEAT));
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ChristmasItems.cookieSeeds.get();
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return 7;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
