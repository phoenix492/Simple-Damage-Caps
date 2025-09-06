package net.phoenix492.simpledamagecaps;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.slf4j.Logger;

@Mod(SimpleDamageCaps.MODID)
public class SimpleDamageCaps {
    public static final String MODID = "simpledamagecaps";
    public static final Logger LOGGER = LogUtils.getLogger();


    public SimpleDamageCaps(IEventBus modEventBus, ModContainer modContainer) {

    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

}

