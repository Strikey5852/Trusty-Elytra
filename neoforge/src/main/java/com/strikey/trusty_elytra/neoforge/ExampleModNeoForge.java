package com.strikey.trusty_elytra.neoforge;

import net.neoforged.fml.common.Mod;

import com.strikey.trusty_elytra.ExampleMod;

@Mod(ExampleMod.MOD_ID)
public final class ExampleModNeoForge {
    public ExampleModNeoForge() {
        // Run our common setup.
        ExampleMod.init();
    }
}
