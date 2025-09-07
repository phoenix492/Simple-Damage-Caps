package net.phoenix492.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.phoenix492.registration.ModDataMaps;
import net.phoenix492.util.TagKeys;

import java.util.concurrent.CompletableFuture;

public class ModDataMapProvider extends DataMapProvider {
    protected ModDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.Provider provider) {
        builder(ModDataMaps.DAMAGE_CAPS_FLAT)
            .add(TagKeys.Entities.DEFAULT_FLAT_DAMAGE_CAP, 15, false)
            .add(TagKeys.Entities.INVINCIBLE, 0, false);

        builder(ModDataMaps.DAMAGE_CAPS_PERCENT)
            .add(TagKeys.Entities.DEFAULT_PERCENTAGE_DAMAGE_CAP, 0.05f, false);
    }
}
