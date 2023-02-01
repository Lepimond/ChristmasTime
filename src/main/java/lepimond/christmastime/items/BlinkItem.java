package lepimond.christmastime.items;

import lepimond.christmastime.entities.BlinkEffect;
import lepimond.christmastime.registry.ChristmasItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
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
        super(ChristmasItems.standardProps().durability(500));
    }

    public InteractionResultHolder<ItemStack> use(Level worldIn, Player player, InteractionHand hand) {
        //The use method is called twice, once on the client, once on the server. I only need the server
        if (worldIn.isClientSide())
            return InteractionResultHolder.fail(new ItemStack(this));

        if (blinkEntity == null) {
            blinkEntity = new BlinkEffect(worldIn, player);
            player.playSound(SoundEvents.ENDERMAN_AMBIENT, 1.0F, 1.0F);
            worldIn.addFreshEntity(blinkEntity);
        } else {
            player.teleportTo(blinkEntity.getX(), blinkEntity.getY(), blinkEntity.getZ());
            player.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);
            blinkEntity.remove(Entity.RemovalReason.DISCARDED);
            blinkEntity = null;

            ItemStack blink = player.getItemInHand(hand);
            blink.setDamageValue(blink.getDamageValue() + 1);
        }

        return InteractionResultHolder.sidedSuccess(new ItemStack(this), worldIn.isClientSide());
    }
}
