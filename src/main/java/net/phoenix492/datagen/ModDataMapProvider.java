package net.phoenix492.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
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
        builder(ModDataMaps.DAMAGE_CAPS)
            .add(TagKeys.Entities.DEFAULT_DAMAGE_CAP, 15, false)
            .add(ResourceLocation.parse("minecraft:warden"), 5, false);
    }
}
