package lepimond.christmastime.entities;

import lepimond.christmastime.registry.ChristmasParticles;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ParticleMonster extends Monster {
    public ParticleMonster(EntityType<? extends ParticleMonster> entityType, Level worldIn) {
        super(entityType, worldIn);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(7, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(8, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public void tick() {
        double particleGap = 0.2;

        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();

        double height = this.dimensions.height;
        double width = this.dimensions.width;

        for (double i = x - width / 2; i < x + width / 2; i += particleGap) {
            this.level.addParticle(ChristmasParticles.monsterParticle.get(), i, y, z - width / 2, 0.0D, 0.0D, 0.0D);
            this.level.addParticle(ChristmasParticles.monsterParticle.get(), i, y, z + width / 2, 0.0D, 0.0D, 0.0D);
            this.level.addParticle(ChristmasParticles.monsterParticle.get(), i, y + height, z - width / 2, 0.0D, 0.0D, 0.0D);
            this.level.addParticle(ChristmasParticles.monsterParticle.get(), i, y + height, z + width / 2, 0.0D, 0.0D, 0.0D);
        }
        for (double j = y; j < y + height; j += particleGap) {
            this.level.addParticle(ChristmasParticles.monsterParticle.get(), x - width / 2, j, z - width / 2, 0.0D, 0.0D, 0.0D);
            this.level.addParticle(ChristmasParticles.monsterParticle.get(), x + width / 2, j, z - width / 2, 0.0D, 0.0D, 0.0D);
            this.level.addParticle(ChristmasParticles.monsterParticle.get(), x - width / 2, j, z + width / 2, 0.0D, 0.0D, 0.0D);
            this.level.addParticle(ChristmasParticles.monsterParticle.get(), x + width / 2, j, z + width / 2, 0.0D, 0.0D, 0.0D);
        }
        for (double k = z - width / 2; k < z + width / 2; k += particleGap) {
            this.level.addParticle(ChristmasParticles.monsterParticle.get(), x - width / 2, y, k, 0.0D, 0.0D, 0.0D);
            this.level.addParticle(ChristmasParticles.monsterParticle.get(), x + width / 2, y, k, 0.0D, 0.0D, 0.0D);
            this.level.addParticle(ChristmasParticles.monsterParticle.get(), x - width / 2, y + height, k, 0.0D, 0.0D, 0.0D);
            this.level.addParticle(ChristmasParticles.monsterParticle.get(), x + width / 2, y + height, k, 0.0D, 0.0D, 0.0D);
        }

        super.tick();
    }
}
