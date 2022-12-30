package lepimond.christmastime.items;

import lepimond.christmastime.registry.ChristmasCreativeTabs;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class LinkedPearlItem extends Item {
    private UUID linkedID;
    private LivingEntity linkedEntity;

    public LinkedPearlItem() {
        super(new Item.Properties().tab(ChristmasCreativeTabs.CHRISTMAS_TAB).stacksTo(1));
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity entity, InteractionHand hand) {
        if (!player.isCrouching())
            return InteractionResult.FAIL;

        this.linkedID = entity.getUUID();
        this.linkedEntity = entity;
        System.out.println("Linked!");
        return InteractionResult.sidedSuccess(player.getLevel().isClientSide);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (player.isCrouching())
            return InteractionResultHolder.fail(stack);

        /*if (linkedID == null) {
            return InteractionResultHolder.fail(stack);
        }*/
        if (linkedEntity == null)
            return InteractionResultHolder.fail(stack);

        worldIn.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENDER_PEARL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (worldIn.getRandom().nextFloat() * 0.4F + 0.8F));
        player.getCooldowns().addCooldown(this, 20);

        if (!worldIn.isClientSide) {
            ThrownEnderpearl thrownPearl = new ThrownEnderpearl(worldIn, linkedEntity);
            thrownPearl.setItem(stack);
            thrownPearl.setPos(player.getX(), player.getY(), player.getZ());
            thrownPearl.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            worldIn.addFreshEntity(thrownPearl);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.getAbilities().instabuild) {
            stack.shrink(1);
        }

        return InteractionResultHolder.sidedSuccess(stack, worldIn.isClientSide());
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        components.add(Component.literal("Drops from Christmas Presents! 8% chance").withStyle(ChatFormatting.AQUA));
    }
}
