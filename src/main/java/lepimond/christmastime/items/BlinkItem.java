package lepimond.christmastime.items;

import lepimond.christmastime.entities.BlinkEffect;
import lepimond.christmastime.registry.ChristmasItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class BlinkItem extends Item {
    private BlinkEffect blinkEntity;

    public BlinkItem() {
        super(ChristmasItems.standardProps());
    }

    public InteractionResultHolder<ItemStack> use(Level worldIn, Player player, InteractionHand hand) {
        //The use method is called twice, once on the client, once on the server. I only need the server
        if (worldIn.isClientSide())
            return InteractionResultHolder.fail(new ItemStack(this));

        if (blinkEntity == null) {
            blinkEntity = new BlinkEffect(worldIn, player);
            worldIn.addFreshEntity(blinkEntity);
        } else {
            player.teleportTo(blinkEntity.getX(), blinkEntity.getY(), blinkEntity.getZ());
            blinkEntity.remove(Entity.RemovalReason.DISCARDED);
            blinkEntity = null;
        }

        return InteractionResultHolder.sidedSuccess(new ItemStack(this), worldIn.isClientSide());
    }
}
