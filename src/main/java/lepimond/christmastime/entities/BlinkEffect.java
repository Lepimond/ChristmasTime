package lepimond.christmastime.entities;

import lepimond.christmastime.items.BlinkItem;
import lepimond.christmastime.registry.ChristmasEntities;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class BlinkEffect extends Entity {
    private Player player;
    private float blinkDistance = 10;

    public BlinkEffect(EntityType<? extends BlinkEffect> entityType, Level worldIn) {
        super(entityType, worldIn);
    }

    public BlinkEffect(Level worldIn, Player player) {
        this(ChristmasEntities.blinkEffect.get(), worldIn);
        this.player = player;
    }

    @Override
    public void tick() {
        super.tick();
        if (player != null) {
            this.setPos(player.getX() + player.getLookAngle().x * blinkDistance,
                    player.getY() + player.getLookAngle().y * blinkDistance,
                    player.getZ() + player.getLookAngle().z * blinkDistance);
        }
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    protected void readAdditionalSaveData(CompoundTag p_20052_) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag p_20139_) {

    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }
}
