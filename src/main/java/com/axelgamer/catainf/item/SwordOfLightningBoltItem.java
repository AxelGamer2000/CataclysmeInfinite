package com.axelgamer.catainf.item;

import com.axelgamer.catainf.geoModel.SwordOfLightningBoltRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.util.RenderUtil;

import java.util.function.Consumer;

public class SwordOfLightningBoltItem extends SwordItem implements GeoItem {
    private static final RawAnimation USED_ANIM = RawAnimation.begin().thenPlay("animation.sword_of_lightning_bolt.used");
    private static final RawAnimation IDLE_ANIM = RawAnimation.begin().thenLoop("animation.sword_of_lightning_bolt.idle");

    private AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public SwordOfLightningBoltItem(Tier tier, Properties properties) {
        super(tier, properties);

        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private SwordOfLightningBoltRenderer renderer;

            @Override
            public @Nullable BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
                if (this.renderer == null) {
                    this.renderer = new SwordOfLightningBoltRenderer();
                }
                
                return this.renderer;
            }
        });
    }

    public PlayState idleCondition(AnimationState animationState) {
        animationState.getController().setAnimation(IDLE_ANIM);
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "idle_controller", 0, this::idleCondition));
        controllers.add(new AnimationController<>(this, "used_controller", 0, state -> PlayState.STOP).triggerableAnim("used_anim", USED_ANIM));
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        Level level = target.level();

        if(level instanceof ServerLevel serverLevel) {
            Entity entity = EntityType.LIGHTNING_BOLT.spawn(serverLevel, new BlockPos(target.getBlockX(), target.getBlockY(), target.getBlockZ()), MobSpawnType.NATURAL);
        }

        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if (level instanceof ServerLevel serverLevel) {
            triggerAnim(player, GeoItem.getOrAssignId(player.getItemInHand(usedHand), serverLevel), "used_controller", "used_anim");
        }
        return super.use(level, player, usedHand);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if(isSelected) {
            if(entity instanceof LivingEntity livingEntity) {
                livingEntity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 60, 0, false, true, true));
            }
        }
        super.inventoryTick(stack, level, entity, slotId, isSelected);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
