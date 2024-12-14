package com.axelgamer.catainf.geoModel;

import com.axelgamer.catainf.CataInf;
import com.axelgamer.catainf.item.SwordOfLightningBoltItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class SwordOfLightningBoltRenderer extends GeoItemRenderer<SwordOfLightningBoltItem> {
    public SwordOfLightningBoltRenderer() {
        super(new DefaultedItemGeoModel<>(ResourceLocation.fromNamespaceAndPath(CataInf.MODID, "sword_of_lightning_bolt")));
    }
}
