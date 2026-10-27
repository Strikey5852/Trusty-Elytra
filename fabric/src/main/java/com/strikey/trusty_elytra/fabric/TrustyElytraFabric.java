package com.strikey.trusty_elytra.fabric;

import net.fabricmc.api.ModInitializer;

import com.strikey.trusty_elytra.TrustyElytra;

public final class TrustyElytraFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        TrustyElytra.init();
    }
}
