package com.strikey.trusty_elytra.neoforge;

import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLPaths;

import com.strikey.trusty_elytra.TrustyElytra;

import java.nio.file.Path;

@Mod(TrustyElytra.MOD_ID)
public final class TrustyElytraNeoForge {
    public TrustyElytraNeoForge() {
        Path configDir = FMLPaths.CONFIGDIR.get();
        Path configFile = configDir.resolve("trusty_elytra.toml");
        TrustyElytra.init(configFile);
    }
}
