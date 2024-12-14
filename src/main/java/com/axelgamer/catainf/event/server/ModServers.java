package com.axelgamer.catainf.event.server;

import com.axelgamer.catainf.CataInf;
import com.axelgamer.catainf.item.ModItems;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.brewing.BrewingRecipe;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import org.lwjgl.system.macosx.MacOSXLibraryDL;

@EventBusSubscriber(modid = CataInf.MODID, bus = EventBusSubscriber.Bus.GAME)
public class ModServers {
    @SubscribeEvent
    public static void registerBrewingRecipes(RegisterBrewingRecipesEvent event) {
        // Gets the builder to add recipes to
        PotionBrewing.Builder builder = event.getBuilder();

        // Will add brewing recipes for all container potions (e.g. potion, splash potion, lingering potion)
        builder.addContainer(ModItems.SYRINGE.get());
        builder.addContainerRecipe(Items.POTION, Items.AMETHYST_SHARD, ModItems.SYRINGE.get());
    }
}
