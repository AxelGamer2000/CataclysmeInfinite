package com.axelgamer.catainf.event.client;

import com.axelgamer.catainf.CataInf;
import com.axelgamer.catainf.item.ChangeTextureTestItem;
import com.axelgamer.catainf.item.ElectricSwordItem;
import com.axelgamer.catainf.item.ModItems;
import com.axelgamer.catainf.item.RealityWatchItem;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.world.item.alchemy.PotionContents;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

public final class ModClients {
    @EventBusSubscriber(modid = CataInf.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
    public static final class ClientBus {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> { // ItemProperties#register is not threadsafe, so we need to call it on the main thread
                ItemProperties.register(
                        // The item to apply the property to.
                        ModItems.CHANGE_TEXTURE_TEST.get(),
                        // The id of the property.
                        ResourceLocation.fromNamespaceAndPath(CataInf.MODID, ChangeTextureTestItem.LEVEL_KEY),
                        // A reference to a method that calculates the override value.
                        // Parameters are the used item stack, the level context, the player using the item,
                        // and a random seed you can use.
                        (stack, level, player, seed) -> ChangeTextureTestItem.GetLevel(stack)
                );
                ItemProperties.register(
                        // The item to apply the property to.
                        ModItems.ELECTRIC_SWORD.get(),
                        // The id of the property.
                        ResourceLocation.fromNamespaceAndPath(CataInf.MODID, ElectricSwordItem.LEVEL_KEY),
                        // A reference to a method that calculates the override value.
                        // Parameters are the used item stack, the level context, the player using the item,
                        // and a random seed you can use.
                        (stack, level, player, seed) -> ElectricSwordItem.GetLevel(stack)
                );
                ItemProperties.register(
                        // The item to apply the property to.
                        ModItems.REALITY_WATCH.get(),
                        // The id of the property.
                        ResourceLocation.fromNamespaceAndPath(CataInf.MODID, RealityWatchItem.ACTIVED_KEY),
                        // A reference to a method that calculates the override value.
                        // Parameters are the used item stack, the level context, the player using the item,
                        // and a random seed you can use.
                        (stack, level, player, seed) -> RealityWatchItem.GetActived(stack)
                );
            });


        }

        @SubscribeEvent
        public static void registerItemColorHandlers(RegisterColorHandlersEvent.Item event) {
            // Parameters are the item stack and the tint index.
            event.register((stack, tintIndex) -> {
                        // Like above, replace with your own calculation. Vanilla values are in the ItemColors class.
                        // Also like above, tint index -1 means no tint and should use a default value instead.
                        if(tintIndex == 1) {
                            return FastColor.ARGB32.opaque(stack.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY).getColor());
                        } else {
                            return 0xFFFFFFFF;
                        }
                    },
                    // A varargs of items to apply the tinting to
                    ModItems.SYRINGE);
        }
    }


}