package com.strikey.trusty_elytra.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import com.strikey.trusty_elytra.ExampleMod;

import java.nio.file.Path;

public final class ExampleModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Path configDir = FabricLoader.getInstance().getConfigDir();
        Path configFile = configDir.resolve("trusty_elytra.toml");
        ExampleMod.init(configFile);
    }
}
