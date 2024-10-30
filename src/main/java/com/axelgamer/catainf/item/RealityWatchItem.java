package com.axelgamer.catainf.item;

import com.axelgamer.catainf.component.ModDataComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class RealityWatchItem extends Item {
    public RealityWatchItem(Properties properties) {
        super(properties.component(ModDataComponents.COORDINATES, Vec3.ZERO).component(ModDataComponents.ACTIVED, false));
    }

    public static final String ACTIVED_KEY = "actived";

    public static float GetActived(ItemStack itemStack) {

        float result;

        if (itemStack.getOrDefault(ModDataComponents.ACTIVED, false)) {
            result = 1.0f;
        } else {
            result = 0.0f;
        }

        return result;
    }

    public static Vec3 GetCoordinates(ItemStack itemStack) {
        return itemStack.getOrDefault(ModDataComponents.COORDINATES, Vec3.ZERO);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {

        ItemStack itemStack = player.getItemInHand(usedHand);

        if(player.isShiftKeyDown()) {
            itemStack.set(ModDataComponents.ACTIVED, true);
            itemStack.set(ModDataComponents.COORDINATES, new Vec3(player.getX(), player.getY(), player.getZ()));
        } else {
            itemStack.set(ModDataComponents.ACTIVED, false);
            if (GetCoordinates(itemStack) == Vec3.ZERO) {
                player.displayClientMessage(Component.literal("Shift-Click in a place for set position !"), true);
            } else {
                player.teleportTo(GetCoordinates(itemStack).x, GetCoordinates(itemStack).y, GetCoordinates(itemStack).z);
                itemStack.set(ModDataComponents.COORDINATES, Vec3.ZERO);
            }
        }

        return super.use(level, player, usedHand);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.literal("Coordinates: " + Math.round(GetCoordinates(stack).x) + " " + Math.round(GetCoordinates(stack).y) + " " + Math.round(GetCoordinates(stack).z)));
    }
}
