package lepimond.christmastime.items;

import lepimond.christmastime.capability.PlayerManaProvider;
import lepimond.christmastime.client.ClientManaData;
import lepimond.christmastime.networking.ManaDataSyncS2CPacket;
import lepimond.christmastime.registry.ChristmasItems;
import lepimond.christmastime.registry.ChristmasMessages;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ManaBottleItem extends Item {
    public ManaBottleItem() {
        super(ChristmasItems.standardProps());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player player, InteractionHand hand) {
        //The use method is called twice, once on the client, once on the server. I only need the server
        if (worldIn.isClientSide())
            return InteractionResultHolder.fail(new ItemStack(this));

        player.getItemInHand(hand).shrink(1);
        player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(mana -> {
            mana.addMana(5);
            ChristmasMessages.sendToPlayer(new ManaDataSyncS2CPacket(mana.getMana()), (ServerPlayer) player);
        });
        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }
}
