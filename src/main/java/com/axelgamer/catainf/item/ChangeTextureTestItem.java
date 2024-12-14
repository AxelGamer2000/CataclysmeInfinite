package com.axelgamer.catainf.item;

import com.axelgamer.catainf.component.ModDataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.Tags;

import java.util.List;

public class ChangeTextureTestItem extends Item {

    public static final String LEVEL_KEY = "level";

    public ChangeTextureTestItem(Properties properties) {
        super(properties.component(ModDataComponents.LEVEL, 1).stacksTo(1));
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
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.literal("Level: " + Math.round(GetLevel(stack))));
    }
}