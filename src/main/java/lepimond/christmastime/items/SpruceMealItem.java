package lepimond.christmastime.items;

import lepimond.christmastime.registry.ChristmasBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.Random;

public class SpruceMealItem extends BoneMealItem {

    private static final int DIR_X_PLUS = 0;
    private static final int DIR_X_MINUS = 1;
    private static final int DIR_Y_PLUS = 2;
    private static final int DIR_Y_MINUS = 3;
    private static final int DIR_Z_PLUS = 4;
    private static final int DIR_Z_MINUS = 5;

    private static final int MAX_HEIGHT = 50;
    private static final int MIN_HEIGHT = 10;

    private static BlockState nonDecayingState = Blocks.SPRUCE_LEAVES.defaultBlockState().setValue(BlockStateProperties.PERSISTENT, true);

    private static Random rand = new Random();


    public SpruceMealItem() {
        super(new Properties().tab(CreativeModeTab.TAB_REDSTONE));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockPos = context.getClickedPos();

        Block block = level.getBlockState(blockPos).getBlock();

        if (block == Blocks.SPRUCE_SAPLING) {
            placeChristmasTree(blockPos, level);
            context.getItemInHand().shrink(1);

            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        return super.useOn(context);
    }

    private void placeChristmasTree(BlockPos pos, Level level) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        int height = MIN_HEIGHT + rand.nextInt(MAX_HEIGHT - MIN_HEIGHT);

        //Doesn't let trees to grow above roof
        for (int i = 1; i < height; i++) {
            BlockPos potentialTreePos = new BlockPos(x, y + i, z);
            Block potentialTreeBlock = level.getBlockState(potentialTreePos).getBlock();

            if (potentialTreeBlock != Blocks.AIR && !(potentialTreeBlock instanceof LeavesBlock)) {
                height = i;
                if (height < MIN_HEIGHT)
                    return;

                break;
            }
        }

        //Main trunk (vertical)
        placeBranch(x, y, z, DIR_Y_PLUS, height, level);

        //Cover of the tree
        placeLeaves(x, y + height, z, level);
        placeLeaves(x + 1, y + height, z, level);
        placeLeaves(x - 1, y + height, z, level);
        placeLeaves(x, y + height, z + 1, level);
        placeLeaves(x, y + height, z - 1, level);
        placeLeaves(x, y + height + 1, z, level);

        //Horizontal branches
        for (int i = 0; i < height / 3; i++) {
            placeBranchWithLeaves(x + 1, y + height / 3 + i * 3, z, DIR_X_PLUS, height * 2 / 9 - i, level);
            placeBranchWithLeaves(x - 1, y + height / 3 + i * 3, z, DIR_X_MINUS, height * 2 / 9 - i, level);
            placeBranchWithLeaves(x, y + height / 3 + i * 3,z + 1, DIR_Z_PLUS, height * 2 / 9 - i, level);
            placeBranchWithLeaves(x, y + height / 3 + i * 3,z - 1, DIR_Z_MINUS, height * 2 / 9 - i, level);
        }
    }

    private void placeBranch(int x, int y, int z, int direction, int length, Level level) {
        for (int i = 0; i < length; i++) {
            int currentX = x;
            int currentY = y;
            int currentZ = z;

            switch (direction) {
                case DIR_X_MINUS:
                    currentX -= i;
                    break;
                case DIR_X_PLUS:
                    currentX += i;
                    break;
                case DIR_Y_MINUS:
                    currentY -= i;
                    break;
                case DIR_Y_PLUS:
                    currentY += i;
                    break;
                case DIR_Z_MINUS:
                    currentZ -= i;
                    break;
                case DIR_Z_PLUS:
                    currentZ += i;
                    break;
            }

            placeLog(Blocks.SPRUCE_LOG, currentX, currentY, currentZ, direction, level);
        }
    }

    private void placeBranchWithLeaves(int x, int y, int z, int direction, int length, Level level) {
        if (length < 0)
            return;

        for (int i = 0; i < length; i++) {
            int currentX = x;
            int currentY = y;
            int currentZ = z;

            switch (direction) {
                case DIR_X_MINUS:
                    currentX -= i;
                    break;
                case DIR_X_PLUS:
                    currentX += i;
                    break;
                case DIR_Y_MINUS:
                    currentY -= i;
                    break;
                case DIR_Y_PLUS:
                    currentY += i;
                    break;
                case DIR_Z_MINUS:
                    currentZ -= i;
                    break;
                case DIR_Z_PLUS:
                    currentZ += i;
                    break;
            }

            if (direction == DIR_X_PLUS || direction == DIR_X_MINUS) {
                placeLeaves(currentX, currentY + 1,    currentZ, level);
                placeLeaves(currentX, currentY - 1,    currentZ, level);
                placeLeaves(currentX,    currentY,     currentZ + 1, level);
                placeLeaves(currentX,    currentY,     currentZ - 1, level);
            } else if (direction == DIR_Y_PLUS || direction == DIR_Y_MINUS) {
                placeLeaves(currentX + 1, currentY,    currentZ, level);
                placeLeaves(currentX - 1, currentY,    currentZ, level);
                placeLeaves(   currentX,     currentY, currentZ + 1, level);
                placeLeaves(   currentX,     currentY, currentZ - 1, level);
            } else {
                placeLeaves(   currentX,     currentY + 1, currentZ, level);
                placeLeaves(   currentX,     currentY - 1, currentZ, level);
                placeLeaves(currentX + 1,    currentY,     currentZ, level);
                placeLeaves(currentX - 1,    currentY,     currentZ, level);
            }

            placeLog(Blocks.SPRUCE_LOG, currentX, currentY, currentZ, direction, level);
        }

        switch (direction) {
            case DIR_X_MINUS:
                placeLeaves(x - length, y, z, level);
                break;
            case DIR_X_PLUS:
                placeLeaves(x + length, y, z, level);
                break;
            case DIR_Y_MINUS:
                placeLeaves(x, y - length, z, level);
                break;
            case DIR_Y_PLUS:
                placeLeaves(x, y + length, z, level);
                break;
            case DIR_Z_MINUS:
                placeLeaves(x, y, z - length, level);
                break;
            case DIR_Z_PLUS:
                placeLeaves(x, y, z + length, level);
                break;
        }
    }

    private void placeLeaves(int x, int y, int z, Level level) {
        BlockPos pos = new BlockPos(x, y, z);

        Block leavesPlaceBlock = level.getBlockState(pos).getBlock();
        if (leavesPlaceBlock != Blocks.AIR)
            return;

        level.setBlock(pos, nonDecayingState, 19);
    }

    private void placeLog(Block block, int x, int y, int z, int direction, Level level) {
        BlockPos pos = new BlockPos(x, y, z);

        Block logPlaceBlock = level.getBlockState(pos).getBlock();
        if (logPlaceBlock != Blocks.AIR && !(logPlaceBlock instanceof LeavesBlock) && !(logPlaceBlock instanceof SaplingBlock))
            return;

        Direction.Axis axis;

        if (direction == DIR_X_PLUS || direction == DIR_X_MINUS) {
            axis = Direction.Axis.X;
        } else if (direction == DIR_Y_PLUS || direction == DIR_Y_MINUS) {
            axis = Direction.Axis.Y;
        } else {
            axis = Direction.Axis.Z;
        }

        level.setBlock(pos, block.defaultBlockState().setValue(BlockStateProperties.AXIS, axis), 19);
    }

    private void placeBlock(Block block, int x, int y, int z, Level level) {
        BlockPos pos = new BlockPos(x, y, z);
        level.setBlock(pos, block.defaultBlockState(), 19);
    }
}
