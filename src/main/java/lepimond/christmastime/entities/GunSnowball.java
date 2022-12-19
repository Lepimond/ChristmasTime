package lepimond.christmastime.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class GunSnowball extends ThrowableItemProjectile {

    public GunSnowball(EntityType<? extends GunSnowball> entityType, Level worldIn) {
        super(entityType, worldIn);
    }

    public GunSnowball(Level worldIn, LivingEntity entity) {
        super(EntityType.SNOWBALL, entity, worldIn);
    }

    public GunSnowball(Level worldIn, double x, double y, double z) {
        super(EntityType.SNOWBALL, x, y, z, worldIn);
    }

    protected Item getDefaultItem() {
        return Items.SNOWBALL;
    }

    private ParticleOptions getParticle() {
        ItemStack itemstack = this.getItemRaw();
        return (ParticleOptions)(itemstack.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemParticleOption(ParticleTypes.ITEM, itemstack));
    }

    public void handleEntityEvent(byte p_37402_) {
        if (p_37402_ == 3) {
            ParticleOptions particleoptions = this.getParticle();

            for(int i = 0; i < 8; ++i) {
                this.level.addParticle(particleoptions, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }

    }

    //Gun Snowball causes significantly more damage than vanilla Snowball
    @Override
    protected void onHitEntity(EntityHitResult hitResult) {
        super.onHitEntity(hitResult);

        Entity entity = hitResult.getEntity();
        int i = entity instanceof Blaze ? 10 : 4;
        entity.hurt(DamageSource.thrown(this, this.getOwner()), (float)i);
    }

    //Gun Snowball spawns a snow block upon landing
    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);

        if (hitResult instanceof EntityHitResult)
            return;

        int x = (int) Math.round(hitResult.getLocation().x + 0.5) - 1;
        int y = (int) Math.round(hitResult.getLocation().y + 0.5) - 1;
        int z = (int) Math.round(hitResult.getLocation().z + 0.5) - 1;
        BlockPos pos = new BlockPos(x, y, z);
        BlockPos underPos = new BlockPos(x, y - 1, z);
        Level level = this.getLevel();

        Block block = level.getBlockState(pos).getBlock();
        Block underBlock = level.getBlockState(underPos).getBlock();

        if (block == Blocks.AIR && underBlock != Blocks.AIR) {
            level.setBlockAndUpdate(pos, Blocks.SNOW.defaultBlockState());

        } else if (block == Blocks.SNOW) { //TODO doesn't work for some reason
            int layers = block.getStateDefinition().any().getValue(BlockStateProperties.LAYERS);
            BlockState snowState = block.getStateDefinition().any();
            snowState.setValue(BlockStateProperties.LAYERS,layers + 1);

            level.setBlockAndUpdate(pos, snowState);
        }
    }
}
