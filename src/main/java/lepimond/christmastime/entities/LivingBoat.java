package lepimond.christmastime.entities;

import lepimond.christmastime.dimensions.BoatDimension;
import lepimond.christmastime.registry.ChristmasBlocks;
import lepimond.christmastime.registry.ChristmasDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class LivingBoat extends Animal {

    //The chance is 1/PORTAL_SPAWN_CHANCE
    private final int PORTAL_SPAWN_CHANCE = 6;

    public static final Predicate<LivingEntity> PREY_SELECTOR = (p_30437_) -> {
        EntityType<?> entitytype = p_30437_.getType();
        return entitytype == EntityType.GOAT;
    };

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
        this.goalSelector.addGoal(1, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(7, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(8, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(9, new NearestAttackableTargetGoal<>(this, Animal.class, false, PREY_SELECTOR));
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

    @Override
    public void die(DamageSource deathCause) {
        super.die(deathCause);

        if (this.level.dimension() == ChristmasDimensions.boatDimensionKey) {
            this.level.explode(this, this.getX(), this.getY(), this.getZ(), 3.0F, Explosion.BlockInteraction.BREAK);
            spawnPortal();
        }
    }

    @Override
    public void thunderHit(ServerLevel level, LightningBolt lightning) {
        this.remove(RemovalReason.DISCARDED);
        spawnPortal();
    }

    private void spawnPortal() {
        level.setBlockAndUpdate(this.getOnPos().above(), ChristmasBlocks.boatPortal.get().defaultBlockState());
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();

        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                for (int k = -2; k <= 2; k++) {
                    if (this.random.nextInt(PORTAL_SPAWN_CHANCE) == 0) {
                        level.setBlockAndUpdate(new BlockPos(x + i, y + j, z + k), ChristmasBlocks.boatPortal.get().defaultBlockState());
                    }

                }
            }
        }
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource p_33814_) {
        return SoundEvents.WOOD_HIT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.WOOD_BREAK;
    }

    public static AttributeSupplier.Builder getExampleAttributes() {
        return Animal.createMobAttributes()
                .add(ForgeMod.STEP_HEIGHT_ADDITION.get(), 2.0F)
                .add(Attributes.MOVEMENT_SPEED, 0.45F)
                .add(Attributes.ATTACK_DAMAGE, 6.0F)
                .add(Attributes.MAX_HEALTH, 40.0F);
    }

    @Override
    public boolean canBeLeashed(Player player) {
        return false;
    }

    public static boolean canSpawn(EntityType<? extends Animal> entityType, LevelAccessor accessor, MobSpawnType spawnType, BlockPos pos, RandomSource source) {
        return Animal.checkAnimalSpawnRules(entityType, accessor, spawnType, pos, source) || accessor.dimensionType() == BoatDimension.boatType;
    }
}
