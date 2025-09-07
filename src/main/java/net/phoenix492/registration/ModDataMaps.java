package net.phoenix492.registration;

import com.mojang.serialization.Codec;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.datamaps.DataMapType;
import net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent;
import net.phoenix492.simpledamagecaps.SimpleDamageCaps;

@EventBusSubscriber
public class ModDataMaps {
    public static final DataMapType<EntityType<?>, Integer> DAMAGE_CAPS_FLAT = DataMapType.builder(
        ResourceLocation.fromNamespaceAndPath(SimpleDamageCaps.MODID, "damage_caps_flat"),
        Registries.ENTITY_TYPE,
        Codec.INT
    ).build();

    public static final DataMapType<EntityType<?>, Float> DAMAGE_CAPS_PERCENT = DataMapType.builder(
        ResourceLocation.fromNamespaceAndPath(SimpleDamageCaps.MODID, "damage_caps_percent"),
        Registries.ENTITY_TYPE,
        Codec.FLOAT
    ).build();



    @SubscribeEvent
    public static void registerDataMapTypes(RegisterDataMapTypesEvent event) {
        event.register(DAMAGE_CAPS_FLAT);
        event.register(DAMAGE_CAPS_PERCENT);
    }
}
