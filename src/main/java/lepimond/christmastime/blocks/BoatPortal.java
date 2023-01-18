package lepimond.christmastime.blocks;

import lepimond.christmastime.registry.ChristmasDimensions;
import lepimond.christmastime.registry.ChristmasParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
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
    public void animateTick(BlockState state, Level worldIn, BlockPos pos, RandomSource rand) {
        float angle = 0;
        float circleRadius = 2;
        int numberOfDots = 80;

        //The actual speed is proportional to 1/rotationSpeed
        float rotationSpeed = 300.0F;

        for(int i = 0; i < numberOfDots; i++) {
            angle += Mth.TWO_PI / numberOfDots;

            float rotationAngle = (worldIn.getGameTime() / rotationSpeed) % Mth.TWO_PI;

            float x = circleRadius * Mth.cos(angle);

            double d0 = (double)pos.getX() + 0.5F + Mth.sin(angle) * circleRadius;
            double d1 = (double)pos.getY() + 0.5F + x * Mth.sin(rotationAngle);
            double d2 = (double)pos.getZ() + 0.5F + Mth.cos(angle) * circleRadius - Mth.sin(rotationAngle / 2) * Mth.sin(rotationAngle / 2) * x * 2;

            double d3 = 0.0D;
            double d4 = 0.0D;
            double d5 = 0.0D;

            float secondCircleRadius = circleRadius / 1.3F;
            float secondX = secondCircleRadius * Mth.cos(angle);

            double d0_2 = (double)pos.getX() + 0.5F + Mth.cos(angle) * secondCircleRadius - Mth.sin(rotationAngle / 2) * Mth.sin(rotationAngle / 2) * secondX * 2;
            double d1_2 = (double)pos.getY() + 0.5F + secondX * Mth.sin(rotationAngle);
            double d2_2 = (double)pos.getZ() + 0.5F + Mth.sin(angle) * secondCircleRadius;

            double d3_2 = 0.0D;
            double d4_2 = 0.0D;
            double d5_2 = 0.0D;

            SimpleParticleType particles;
            if (worldIn.dimension() == ChristmasDimensions.boatDimensionKey) {
                particles = ParticleTypes.PORTAL;
            } else {
                particles = ChristmasParticles.boatParticle.get();
            }

            worldIn.addParticle(particles, d0, d1, d2, d3, d4, d5);
            worldIn.addParticle(particles, d0_2, d1_2, d2_2, d3_2, d4_2, d5_2);
        }
    }
}
