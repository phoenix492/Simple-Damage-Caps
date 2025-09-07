package net.phoenix492.eventhandler;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityInvulnerabilityCheckEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.phoenix492.registration.ModDataMaps;
import net.phoenix492.simpledamagecaps.SimpleDamageCaps;
import net.phoenix492.util.TagKeys;

@EventBusSubscriber(modid = SimpleDamageCaps.MODID)
public class DamageEventHandler {

    /**
     * Here I've chosen to go for the stylistic choice of flagging an entity as invulnerable (so the attack visually appears to do nothing)
     * if the damage cap is set to a value that would always reduce damage to zero.
     */
    @SubscribeEvent
    public static void invulnCheckEvent(EntityInvulnerabilityCheckEvent event) {
        if (!(event.getEntity() instanceof LivingEntity entity)) {
            return;
        }

        // IntelliJ's linter yells at me if I don't check for this being empty when I go to check its datatype.
        // I'm pretty sure this should be literally impossible but a sanity check doesn't hurt I suppose.
        if (BuiltInRegistries.ENTITY_TYPE.getResourceKey(entity.getType()).isEmpty()) {
            return;
        }

        // Don't cap any damage types that are tagged to ignore the damage cap introduced by this mod.
        if (event.getSource().is(TagKeys.DamageTypes.BYPASSES_DAMAGE_CAP)) {
            return;
        }

        Integer flatCap = BuiltInRegistries.ENTITY_TYPE.getData(ModDataMaps.DAMAGE_CAPS_FLAT, BuiltInRegistries.ENTITY_TYPE.getResourceKey(entity.getType()).get());
        Float percentageCap = BuiltInRegistries.ENTITY_TYPE.getData(ModDataMaps.DAMAGE_CAPS_PERCENT, BuiltInRegistries.ENTITY_TYPE.getResourceKey(entity.getType()).get());

        if (flatCap != null && flatCap <= 0) {
            event.setInvulnerable(true);
        } else if (percentageCap != null && percentageCap <= 0) {
            event.setInvulnerable(true);
        }

    }

    @SubscribeEvent
    public static void damageEvent(LivingDamageEvent.Pre event) {
        EntityType<?> entityType = event.getEntity().getType();

        // IntelliJ's linter yells at me if I don't check for this being empty when I go to check its datatype.
        // I'm pretty sure this should be literally impossible but a sanity check doesn't hurt I suppose.
        if (BuiltInRegistries.ENTITY_TYPE.getResourceKey(entityType).isEmpty()) {
            return;
        }

        // Don't cap any damage types that are tagged to ignore the damage cap introduced by this mod.
        if (event.getSource().is(TagKeys.DamageTypes.BYPASSES_DAMAGE_CAP)) {
            return;
        }

        Integer flatCap = BuiltInRegistries.ENTITY_TYPE.getData(ModDataMaps.DAMAGE_CAPS_FLAT, BuiltInRegistries.ENTITY_TYPE.getResourceKey(entityType).get());
        Float percentageCap = BuiltInRegistries.ENTITY_TYPE.getData(ModDataMaps.DAMAGE_CAPS_PERCENT, BuiltInRegistries.ENTITY_TYPE.getResourceKey(entityType).get());

       if (flatCap == null && percentageCap == null) {
           // Unnecessary but explicitly marking the return here improves readability IMO.
           return;
       } else if (percentageCap == null) {
           if (event.getNewDamage() > flatCap) {
               event.setNewDamage(flatCap);
           }
       } else if (flatCap == null) {
           if (event.getNewDamage() > event.getEntity().getMaxHealth() * percentageCap) {
               event.setNewDamage(percentageCap * event.getEntity().getMaxHealth());
           }
       } else {
           // In the event both caps are set, just cap it to the smallest of the two.
           event.setNewDamage(Math.min(flatCap, percentageCap*event.getEntity().getMaxHealth()));
       }

    }
}
