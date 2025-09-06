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
    public static final DataMapType<EntityType<?>, Integer> DAMAGE_CAPS = DataMapType.builder(
        ResourceLocation.fromNamespaceAndPath(SimpleDamageCaps.MODID, "damage_caps"),
        Registries.ENTITY_TYPE,
        Codec.INT
    ).build();

    @SubscribeEvent
    public static void registerDataMapTypes(RegisterDataMapTypesEvent event) {
        event.register(DAMAGE_CAPS);
    }
}
