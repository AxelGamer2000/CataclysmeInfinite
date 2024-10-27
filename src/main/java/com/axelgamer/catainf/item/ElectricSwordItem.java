package com.axelgamer.catainf.item;

import com.axelgamer.catainf.component.ModDataComponents;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.Level;

import java.util.List;

public class ElectricSwordItem extends SwordItem {

    public static float DEFAULT_DAMAGE = 4;

    public static String LEVEL_KEY = "level";

    public ElectricSwordItem(Tier tier, Properties properties) {
        super(tier, properties.component(ModDataComponents.LEVEL, 1));
    }

    public static float GetLevel(ItemStack itemStack) {
        return itemStack.getOrDefault(ModDataComponents.LEVEL, 1);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {

        ItemStack itemStack = player.getItemInHand(usedHand);

        if(player.isShiftKeyDown()) {
            if(GetLevel(itemStack) >= 3) {
                itemStack.set(ModDataComponents.LEVEL, 1);
            } else {
                itemStack.set(ModDataComponents.LEVEL, itemStack.getOrDefault(ModDataComponents.LEVEL, 1) + 1);
            }

            player.displayClientMessage(Component.literal("Level: " + Math.round(GetLevel(itemStack))), true);

        }

        return super.use(level, player, usedHand);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {

        if(entity instanceof Player player) {
            if(isSelected) {
                if(GetLevel(stack) == 1) {
                    stack.set(DataComponents.ATTRIBUTE_MODIFIERS, createSwordAttributes(Tiers.DIAMOND,(float) DEFAULT_DAMAGE * 1 - 1, -2.4F));
                } else if (GetLevel(stack) == 2) {
                    stack.set(DataComponents.ATTRIBUTE_MODIFIERS, createSwordAttributes(Tiers.DIAMOND,(float) DEFAULT_DAMAGE * 2 - 1, -2.4F));
                } else if (GetLevel(stack) == 3) {
                    stack.set(DataComponents.ATTRIBUTE_MODIFIERS, createSwordAttributes(Tiers.DIAMOND,(float) DEFAULT_DAMAGE * 4 - 1, -2.4F));
                }
            }
        }


        super.inventoryTick(stack, level, entity, slotId, isSelected);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.literal("Level: " + Math.round(GetLevel(stack)) + ", " + ((DEFAULT_DAMAGE * Math.pow(2, GetLevel(stack) - 1)))));
    }

    public static ItemAttributeModifiers createSwordAttributes(Tier p_330371_, float p_331976_, float p_332104_) {
        return ItemAttributeModifiers.builder()
                .add(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(
                                BASE_ATTACK_DAMAGE_ID, (double)((float)p_331976_), AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(
                        Attributes.ATTACK_SPEED,
                        new AttributeModifier(BASE_ATTACK_SPEED_ID, (double)p_332104_, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                )
                .build();
    }

}
