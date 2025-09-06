package net.phoenix492.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EntityType;
import net.phoenix492.simpledamagecaps.SimpleDamageCaps;

public class TagKeys {
    public static class Entities {
        public static final TagKey<EntityType<?>> DEFAULT_DAMAGE_CAP = createTag(SimpleDamageCaps.MODID, "default_damage_cap");

        private static TagKey<EntityType<?>> createTag(String namespace, String name) {
            return TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(namespace, name));
        }
    }
    public static class DamageTypes {
        public static final TagKey<DamageType> BYPASSES_DAMAGE_CAP = createTag(SimpleDamageCaps.MODID, "bypasses_damage_cap");
        public static final TagKey<DamageType> BYPASSES_INVULNERABILITY = createTag("minecraft", "bypasses_invulnerability");


        private static TagKey<DamageType> createTag(String namespace, String name) {
            return TagKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(namespace, name));
        }
    }
}
