package net.phoenix492.eventhandler;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.phoenix492.simpledamagecaps.SimpleDamageCaps;
import net.phoenix492.util.TagKeys;

@EventBusSubscriber(modid = SimpleDamageCaps.MODID)
public class DamageEventHandler {

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

        // Temporary code to test functionality while I try to figure out why custom datamaps aren't working.
        if (entityType.is(TagKeys.Entities.DEFAULT_DAMAGE_CAP) && event.getNewDamage() > 15f) {
            event.setNewDamage(15f);
        }

//        Integer dataCap = BuiltInRegistries.ENTITY_TYPE.getData(ModDataMaps.DAMAGE_CAPS, BuiltInRegistries.ENTITY_TYPE.getResourceKey(entityType).get());
//
//        if (dataCap == null) {
//            return;
//        }
//
//        if (event.getNewDamage() > dataCap) {
//            event.setNewDamage(dataCap);
//        }

    }

}
