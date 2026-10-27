package com.strikey.trusty_elytra.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import com.strikey.trusty_elytra.TrustyElytra;

import java.nio.file.Path;

public final class TrustyElytraFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Path configDir = FabricLoader.getInstance().getConfigDir();
        Path configFile = configDir.resolve("trusty_elytra.toml");
        TrustyElytra.init(configFile);
    }
}
