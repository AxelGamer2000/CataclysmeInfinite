package com.axelgamer.catainf.item;

import com.axelgamer.catainf.component.ModDataComponents;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;

public class PassCardItem extends Item {
    public PassCardItem(Properties properties) {
        super(properties.stacksTo(1).component(ModDataComponents.PASSCARD_ID, 0).component(DataComponents.DYED_COLOR, new DyedItemColor(DyedItemColor.LEATHER_COLOR, true)));
    }

    public DyedItemColor getColor(ItemStack itemStack) {
        return itemStack.getOrDefault(DataComponents.DYED_COLOR, new DyedItemColor(DyedItemColor.LEATHER_COLOR, true));
    }

    public int getPassCardID(ItemStack itemStack) {
        return itemStack.getOrDefault(ModDataComponents.PASSCARD_ID, 0);
    }

}
