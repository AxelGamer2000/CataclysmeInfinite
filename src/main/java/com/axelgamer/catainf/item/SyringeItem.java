package com.axelgamer.catainf.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SyringeItem extends PotionItem{
    public SyringeItem(Properties properties) {
        super(properties.component(DataComponents.POTION_CONTENTS, new PotionContents(Potions.WATER)));
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {

        Level level = player.level();

        if(entity instanceof LivingEntity livingEntity) {
            if (!level.isClientSide) {
                PotionContents potioncontents = stack.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
                potioncontents.forEachEffect(effect -> {
                    if (effect.getEffect().value().isInstantenous()) {
                        effect.getEffect().value().applyInstantenousEffect(player, player, livingEntity, effect.getAmplifier(), 1.0);
                    } else {
                        livingEntity.addEffect(effect);
                    }
                });
            }
        }

        if(!player.isCreative()) {
            stack.consume(1, player);
            player.addItem(new ItemStack(ModItems.EMPTY_SYRINGE.get()));
        }


        return super.onLeftClickEntity(stack, player, entity);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        PotionContents potioncontents = stack.get(DataComponents.POTION_CONTENTS);
        if (potioncontents != null) {
            potioncontents.addPotionTooltip(tooltipComponents::add, 1.0F, context.tickRate());
        }
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack itemstack = super.getDefaultInstance();
        itemstack.set(DataComponents.POTION_CONTENTS, new PotionContents(Potions.WATER));
        return itemstack;
    }

    /**
     * Called when the player finishes using this Item (E.g. finishes eating.). Not called when the player stops using the Item before the action is complete.
     */
    @Override
    public @NotNull ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entityLiving) {
        return stack;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        return InteractionResult.PASS;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 32;
    }

    /**
     * Returns the action that specifies what animation to play when the item is being used.
     */
    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return super.getUseAnimation(stack);
    }

    /**
     * Called to trigger the item's "innate" right click behavior. To handle when this item is used on a Block, see {@link #onItemUse}.
     */
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }
    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have different names based on their damage or NBT.
     */

    @Override
    public String getDescriptionId(ItemStack stack) {
        return this.getDescriptionId();
    }
}
