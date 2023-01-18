package lepimond.christmastime.blocks;

import lepimond.christmastime.registry.ChristmasDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class BoatPortal extends Block {

    public BoatPortal() {
        super(Properties.of(Material.PORTAL).noCollission().randomTicks());
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if(entity instanceof ServerPlayer player) {
            if(player.isOnPortalCooldown()) {
                player.setPortalCooldown();
            }
            else {
                Level playerWorld = player.level;
                MinecraftServer server = playerWorld.getServer();
                ResourceKey<Level> destination = (entity.level.dimension() == ChristmasDimensions.boatDimensionKey) ? Level.OVERWORLD : ChristmasDimensions.boatDimensionKey;
                if(server != null) {
                    ServerLevel destinationWorld = server.getLevel(destination);
                    if(destinationWorld != null) {
                        player.setPortalCooldown();
                        //Entity.changeDimension() is hardcoded to only work with vanilla-like portals. Better use Entity.teleportTo()
                        player.teleportTo(destinationWorld, entity.getX(), entity.getY(), entity.getZ(), 0.0F, 0.0F);
                    }
                }
            }
        }
    }

    @Override
    public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
        super.tick(state, worldIn, pos, rand);
        if (worldIn.dimension() == ChristmasDimensions.boatDimensionKey)
            return;

        worldIn.removeBlock(pos, false);
    }

    @Override
    public void animateTick(BlockState state, Level worldIn, BlockPos pos, RandomSource rand) {
        for(int i = 0; i < 8; i++) {
            double d0 = (double)pos.getX() + rand.nextDouble();
            double d1 = (double)pos.getY() + rand.nextDouble();
            double d2 = (double)pos.getZ() + rand.nextDouble();
            double d3 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            double d4 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            double d5 = ((double)rand.nextFloat() - 0.5D) * 0.5D;

            SimpleParticleType particles;
            if (worldIn.dimension() == ChristmasDimensions.boatDimensionKey) {
                particles = ParticleTypes.PORTAL;
            } else {
                particles = ParticleTypes.REVERSE_PORTAL;
            }

            worldIn.addParticle(particles, d0, d1, d2, d3, d4, d5);
        }
    }
}
