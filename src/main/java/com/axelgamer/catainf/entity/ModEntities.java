package com.axelgamer.catainf.entity;

import com.axelgamer.catainf.CataInf;
import com.axelgamer.catainf.entity.projectiles.Dynamite;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.projectile.Snowball;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, CataInf.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<Dynamite>> DYNAMITE = ENTITY_TYPES.register(
            "dynamite", () -> EntityType.Builder.<Dynamite>of(Dynamite::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("dynamite"));
}
