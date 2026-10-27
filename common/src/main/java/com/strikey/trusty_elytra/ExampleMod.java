package com.strikey.trusty_elytra;

import java.nio.file.Path;

public final class ExampleMod {
    public static final String MOD_ID = "trusty_elytra";

    public static void init(Path configPath) {
        Config.init(configPath);
    }
}
