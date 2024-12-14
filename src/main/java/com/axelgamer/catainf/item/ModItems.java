package com.axelgamer.catainf.item;

import com.axelgamer.catainf.CataInf;
import com.axelgamer.catainf.block.ModBlocks;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Unbreakable;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    // Create a Deferred Register to hold Items which will all be registered under the "examplemod" namespace
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CataInf.MODID);

    // Creates a new BlockItem with the id "examplemod:example_block", combining the namespace and path
    public static final DeferredItem<BlockItem> EXAMPLE_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("example_block", ModBlocks.EXAMPLE_BLOCK);

    public static final DeferredItem<BlockItem> PASSCARD_READER_ITEM = ITEMS.registerSimpleBlockItem("passcard_reader", ModBlocks.PASSCARD_READER);


    // Creates a new food item with the id "examplemod:example_id", nutrition 1 and saturation 2
    public static final DeferredItem<Item> CHANGE_TEXTURE_TEST = ITEMS.register(
            "change_texture_test",
            () -> new ChangeTextureTestItem(new Item.Properties())
    );

    public static final DeferredItem<Item> ELECTRIC_SWORD = ITEMS.register(
            "electric_sword",
            () -> new ElectricSwordItem(Tiers.DIAMOND, new Item.Properties().attributes(SwordItem.createAttributes(Tiers.DIAMOND, 0, 0)).component(DataComponents.UNBREAKABLE, new Unbreakable(true)))
    );

    public static final DeferredItem<Item> REALITY_WATCH = ITEMS.register(
            "reality_watch",
            () -> new RealityWatchItem(new Item.Properties().stacksTo(1))
    );

    // first: Dynamite
    // second: Lightning Bolt Sword
    // twitchs items: Golden Bone, Portative Bunker

    public static final DeferredItem<Item> DYNAMITE = ITEMS.register(
            "dynamite",
            () -> new DynamiteItem(new Item.Properties().stacksTo(16))
    );

    public static final DeferredItem<Item> SWORD_OF_LIGHTNING_SWORD = ITEMS.register(
            "sword_of_lightning_bolt",
            () -> new SwordOfLightningBoltItem(Tiers.NETHERITE, new Item.Properties().attributes(SwordItem.createAttributes(Tiers.NETHERITE, 6f, -2.4f)).durability(5459))
    );

    // items:  and syringe
    // first: syringe
    // second: PassCard & PassCard Reader
    // tempory stopped

    public static final DeferredItem<Item> SYRINGE = ITEMS.register(
            "syringe",
            () -> new SyringeItem(new Item.Properties())
    );

    public static final DeferredItem<Item> EMPTY_SYRINGE = ITEMS.register(
            "empty_syringe",
            () -> new EmptySyringeItem(new Item.Properties())
    );

    public static final DeferredItem<Item> PASSCARD = ITEMS.register(
            "passcard",
            () -> new PassCardItem(new Item.Properties())
    );

    // items: frozen zombie and crab pinch

}