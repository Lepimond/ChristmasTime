package lepimond.christmastime.entities;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import org.jetbrains.annotations.Nullable;

public class LivingBoat extends Animal { ;

    public LivingBoat(EntityType<? extends Animal> type, Level level) {
        super(type, level);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
        return null;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(7, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public void tick() {
        super.tick();
        if (this.isInWater()) {
            Vec3 pos = this.position();
            this.remove(RemovalReason.DISCARDED);

            Boat vanillaBoat = new Boat(level, pos.x, pos.y, pos.z);
            vanillaBoat.setType(Boat.Type.SPRUCE);
            level.addFreshEntity(vanillaBoat);
        }
    }

    public static AttributeSupplier.Builder getExampleAttributes() {
        return Animal.createMobAttributes()
                .add(ForgeMod.STEP_HEIGHT_ADDITION.get(), 2.0F)
                .add(Attributes.MOVEMENT_SPEED, 0.45F)
                .add(Attributes.ATTACK_DAMAGE, 6.0F)
                .add(Attributes.MAX_HEALTH, 40.0F);
    }
}
