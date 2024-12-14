package com.axelgamer.catainf.creativeTab;

import com.axelgamer.catainf.CataInf;
import com.axelgamer.catainf.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeTabs {

    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CataInf.MODID);

    // Creates a creative tab with the id "examplemod:example_tab" for the example item, that is placed after the combat tab
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("example_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.examplemod")) //The language key for the title of your CreativeModeTab
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> ModItems.CHANGE_TEXTURE_TEST.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(ModItems.CHANGE_TEXTURE_TEST.get());
                output.accept(ModItems.ELECTRIC_SWORD.get());
                output.accept(ModItems.REALITY_WATCH.get());
                output.accept(ModItems.DYNAMITE.get());
                output.accept(ModItems.SWORD_OF_LIGHTNING_SWORD.get());
                output.accept(ModItems.EMPTY_SYRINGE.get());
                output.accept(ModItems.SYRINGE.get());
                output.accept(ModItems.PASSCARD.get());
                output.accept(ModItems.PASSCARD_READER_ITEM.get());
            }).build());

}
