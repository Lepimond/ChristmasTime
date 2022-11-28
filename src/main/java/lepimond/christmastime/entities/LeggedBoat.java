package lepimond.christmastime.entities;

import lepimond.christmastime.items.LeggedBoatItem;
import lepimond.christmastime.registry.ChristmasItems;
import net.minecraft.network.protocol.game.ServerboundPaddleBoatPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages;

public class LeggedBoat extends Boat {

    private boolean leggedInputLeft;
    private boolean leggedInputRight;
    private boolean leggedInputUp;
    private boolean leggedInputDown;

    public LeggedBoat(EntityType<? extends Boat> entityType, Level worldIn) {
        super(entityType, worldIn);
        this.blocksBuilding = true;

        this.maxUpStep = 1.5F;
    }

    public LeggedBoat(Level worldIn, double x, double y, double z) {
        this(ChristmasEntityTypes.leggedBoat.get(), worldIn);
        this.setPos(x, y, z);
        this.setDeltaMovement(Vec3.ZERO);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    public LeggedBoat(PlayMessages.SpawnEntity packet, Level worldIn) {
        super(ChristmasEntityTypes.leggedBoat.get(), worldIn);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.isControlledByLocalInstance() && this.level.isClientSide) {
            this.controlLeggedBoat();
            this.level.sendPacketToServer(new ServerboundPaddleBoatPacket(this.getPaddleState(0), this.getPaddleState(1)));
        }
    }

    private void controlLeggedBoat() {
        if (this.isVehicle()) {
            float f = 0.0F; //Boat's speed
            float d = 0.0F; //Boat's rotational speed
            if (this.leggedInputLeft) {
                d -= 3;
            }

            if (this.leggedInputRight) {
                d += 3;
            }

            if (this.leggedInputRight != this.leggedInputLeft && !this.leggedInputUp && !this.leggedInputDown) {
                f += 0.005F;
            }

            this.setYRot(this.getYRot() + d);
            if (this.leggedInputUp) {
                f += 0.5F;
            }

            if (this.leggedInputDown) {
                f -= 0.2F;
            }

            if(this.isUnderWater()) {
                f /= 1.0F;
            } else if (this.isInWater()) {
                f /= 6.0F;
            } else if(!this.isOnGround()) {
                f = 0.0F;
            }

            this.setDeltaMovement(this.getDeltaMovement().add((double)(Mth.sin(-this.getYRot() * ((float)Math.PI / 180F)) * f), 0.0D, (double)(Mth.cos(this.getYRot() * ((float)Math.PI / 180F)) * f)));
            this.setPaddleState(this.leggedInputRight && !this.leggedInputLeft || this.leggedInputUp, this.leggedInputLeft && !this.leggedInputRight || this.leggedInputUp);
        }
    }

    //Made it never eject passengers (for successful underwater travel)
    @Override
    public void ejectPassengers() {

    }

    @Override
    public void positionRider(Entity p_38379_) {
        if (this.hasPassenger(p_38379_)) {
            float f = this.getSinglePassengerXOffset();
            float f1 = (float)((this.isRemoved() ? (double)0.01F : this.getPassengersRidingOffset()) + p_38379_.getMyRidingOffset());
            if (this.getPassengers().size() > 1) {
                int i = this.getPassengers().indexOf(p_38379_);
                if (i == 0) {
                    f = 0.2F;
                } else {
                    f = -0.6F;
                }

                if (p_38379_ instanceof Animal) {
                    f += 0.2F;
                }
            }

            Vec3 vec3 = (new Vec3((double)f, 0.0D, 0.0D)).yRot(-this.getYRot() * ((float)Math.PI / 180F) - ((float)Math.PI / 2F));
            p_38379_.setPos(this.getX() + vec3.x, this.getY() + (double)f1 + 0.7F, this.getZ() + vec3.z);
            //TODO p_38379_.setYRot(p_38379_.getYRot() + this.deltaRotation);
            //TODO p_38379_.setYHeadRot(p_38379_.getYHeadRot() + this.deltaRotation);
            this.clampRotation(p_38379_);
            if (p_38379_ instanceof Animal && this.getPassengers().size() == this.getMaxPassengers()) {
                int j = p_38379_.getId() % 2 == 0 ? 90 : 270;
                p_38379_.setYBodyRot(((Animal)p_38379_).yBodyRot + (float)j);
                p_38379_.setYHeadRot(p_38379_.getYHeadRot() + (float)j);
            }

        }
    }

    @Override
    protected int getMaxPassengers() {
        return 1;
    }

    @Override
    public void setInput(boolean p_38343_, boolean p_38344_, boolean p_38345_, boolean p_38346_) {
        leggedInputLeft = p_38343_;
        leggedInputRight = p_38344_;
        leggedInputUp = p_38345_;
        leggedInputDown = p_38346_;
    }

    public Item getDropItem() {
        return ChristmasItems.leggedBoat.get();
    }
}
