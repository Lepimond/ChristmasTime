package lepimond.christmastime.blocks;

import lepimond.christmastime.registry.ChristmasDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.portal.PortalForcer;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;

public class BoatPortal extends Block {
    public BoatPortal() {
        super(BlockBehaviour.Properties.of(Material.PORTAL).noCollission());
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        /**if (level instanceof ServerLevel && entity.canChangeDimensions()) {
            if(entity.isOnPortalCooldown()) {
                entity.setPortalCooldown();
            } else {
                ResourceKey<Level> destination = (level.dimension() == Level.NETHER) ? Level.OVERWORLD : Level.NETHER;
                System.out.println(destination);
                ServerLevel destinationWorld = ((ServerLevel)level).getServer().getLevel(destination);
                if(destinationWorld != null && !entity.isPassenger()) {
                    level.getProfiler().push("portal");
                    entity.setPortalCooldown();
                    System.out.println("Before dimension change");
                    entity.changeDimension(destinationWorld, new PortalForcer(destinationWorld));
                    System.out.println("After dimension change");
                    level.getProfiler().pop();
                }
            }

        }*/

        if (level instanceof ServerLevel && !entity.isPassenger() && !entity.isVehicle() && entity.canChangeDimensions()) {
            if(entity.isOnPortalCooldown()) {
                entity.setPortalCooldown();
            } else {
                if (!entity.level.isClientSide() && !pos.equals(entity.portalEntrancePos)) {
                    entity.portalEntrancePos = pos.immutable();
                }

                entity.isInsidePortal = true;

                ResourceKey<Level> resourcekey = (level.dimension() == ChristmasDimensions.boatDimensionKey) ? Level.OVERWORLD : ChristmasDimensions.boatDimensionKey;
                ServerLevel destinationWorld = ((ServerLevel)level).getServer().getLevel(resourcekey);
                if (destinationWorld == null) {
                    return;
                }

                entity.level.getProfiler().push("boat_portal");
                entity.setPortalCooldown();
                System.out.println("Before dimension change");
                entity.changeDimension(destinationWorld);
                System.out.println("After dimension change");
                entity.level.getProfiler().pop();
            }
        }
    }
}
