package lepimond.christmastime.items;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

public class SnowGunItem extends Item {

    public static final float DEFAULT_POWER = 3.0F;

    public SnowGunItem() {
        super(new Properties().tab(CreativeModeTab.TAB_REDSTONE).durability(500));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        Snowball snowball = new Snowball(level, player);
        snowball.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, DEFAULT_POWER);
        level.addFreshEntity(snowball);

        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));

        ItemStack snowGun = player.getItemInHand(hand);
        snowGun.setDamageValue(snowGun.getDamageValue() + 1);

        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }
}