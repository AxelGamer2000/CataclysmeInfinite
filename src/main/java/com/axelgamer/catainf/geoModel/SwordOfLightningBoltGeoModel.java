package com.axelgamer.catainf.geoModel;

import com.axelgamer.catainf.CataInf;
import com.axelgamer.catainf.item.ModItems;
import com.axelgamer.catainf.item.SwordOfLightningBoltItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;

public class SwordOfLightningBoltGeoModel extends GeoModel<SwordOfLightningBoltItem> {

    private final ResourceLocation model = ResourceLocation.fromNamespaceAndPath(CataInf.MODID, "geo/item/sword_of_lightning_bolt.geo.json");
    private final ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(CataInf.MODID, "textures/item/sword_of_lightning_bolt.png");
    private final ResourceLocation animations = ResourceLocation.fromNamespaceAndPath(CataInf.MODID, "animations/sword_of_lightning_bolt.animation.json");


    @Override
    public ResourceLocation getModelResource(SwordOfLightningBoltItem animatable) {
        return this.model;
    }

    @Override
    public ResourceLocation getTextureResource(SwordOfLightningBoltItem animatable) {
        return this.texture;
    }

    @Override
    public ResourceLocation getAnimationResource(SwordOfLightningBoltItem animatable) {
        return this.animations;
    }
}
