package lepimond.christmastime.blocks;

import lepimond.christmastime.registry.ChristmasDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class BoatPortal extends Block {

    public BoatPortal() {
        super(Properties.of(Material.PORTAL).noCollission());
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
                        player.teleportTo(destinationWorld, entity.getX(), entity.getY(), entity.getZ(), 0.0F, 0.0F);
                    }
                }
            }
        }
    }
}
