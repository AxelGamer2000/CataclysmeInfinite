package com.axelgamer.catainf.entity.projectiles;

import com.axelgamer.catainf.entity.ModEntities;
import com.axelgamer.catainf.item.ModItems;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;

public class Dynamite extends ThrowableItemProjectile {
    private static final Item DEFAULT_ITEM = ModItems.DYNAMITE.get();
    private static final EntityType<Dynamite> ENTITY_TYPE = ModEntities.DYNAMITE.get();

    public Dynamite(EntityType<? extends ThrowableItemProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public Dynamite(double x, double y, double z, Level level) {
        super(ENTITY_TYPE, x, y, z, level);
    }

    public Dynamite(LivingEntity shooter, Level level) {
        super(ENTITY_TYPE, shooter, level);
    }


    @Override
    protected @NotNull Item getDefaultItem() {
        return DEFAULT_ITEM;
    }

    @Override
    public @NotNull ItemStack getItem() {
        return new ItemStack(DEFAULT_ITEM);
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        this.level().explode(this.getOwner(), result.getEntity().getX(), result.getEntity().getY(), result.getEntity().getZ(), 2.0f, Level.ExplosionInteraction.TNT);
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        this.level().explode(this.getOwner(), result.getBlockPos().getX(), result.getBlockPos().getY(), result.getBlockPos().getZ(), 2.0f, Level.ExplosionInteraction.TNT);
    }

    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();
        }
    }

    private ParticleOptions getParticle() {
        ItemStack itemstack = this.getItem();
        return (ParticleOptions)(new ItemParticleOption(ParticleTypes.ITEM, itemstack));
    }

    @Override
    public void handleEntityEvent(byte pId) {
        if (pId == 3) {
            ParticleOptions particleoptions = this.getParticle();

            for (int i = 0; i < 8; i++) {
                this.level().addParticle(particleoptions, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
            }
        }
    }
}
