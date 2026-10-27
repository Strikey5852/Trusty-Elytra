package com.strikey.trusty_elytra.neoforge;

import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLPaths;

import com.strikey.trusty_elytra.ExampleMod;

import java.nio.file.Path;

@Mod(ExampleMod.MOD_ID)
public final class ExampleModNeoForge {
    public ExampleModNeoForge() {
        Path configDir = FMLPaths.CONFIGDIR.get();
        Path configFile = configDir.resolve("trusty_elytra.toml");
        ExampleMod.init(configFile);
    }
}
