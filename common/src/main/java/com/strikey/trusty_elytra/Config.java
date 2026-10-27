package com.strikey.trusty_elytra;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public final class Config {
    public static final String KEY_ELYTRA_BREAK_VELOCITY_MULTIPLIER = "elytraBreakVelocityMultiplier";
    public static final float DEFAULT_ELYTRA_BREAK_VELOCITY_MULTIPLIER = 0.5f;

    private static volatile Config instance;

    private final Path configPath;
    private float elytraBreakVelocityMultiplier = DEFAULT_ELYTRA_BREAK_VELOCITY_MULTIPLIER;

    public static Config get() {
        return instance;
    }

    public static void init(Path configPath) {
        instance = new Config(configPath);
    }

    private Config(Path configPath) {
        this.configPath = configPath;
        load();
    }

    public float getElytraBreakVelocityMultiplier() {
        return elytraBreakVelocityMultiplier;
    }

    private void load() {
        try {
            if (Files.exists(configPath)) {
                try (InputStream in = Files.newInputStream(configPath)) {
                    String content = new String(in.readAllBytes());
                    for (String line : content.split("\\r?\\n")) {
                        String trimmed = line.trim();
                        if (trimmed.isEmpty() || trimmed.startsWith("#")) continue;
                        String[] parts = trimmed.split("=", 2);
                        if (parts.length == 2) {
                            String key = parts[0].trim();
                            String value = parts[1].trim().replace("\"", "").replace("'", "").trim();
                            if (key.equals(KEY_ELYTRA_BREAK_VELOCITY_MULTIPLIER)) {
                                try {
                                    elytraBreakVelocityMultiplier = Math.clamp(Float.parseFloat(value), 0.0f, 1.0f);
                                } catch (NumberFormatException e) {
                                    // Keep default if invalid
                                }
                            }
                        }
                    }
                }
            } else {
                createDefaultConfig();
            }
        } catch (IOException e) {
            // Config loading failed; fall back to default
        }
    }

    private void createDefaultConfig() {
        try {
            Files.createDirectories(configPath.getParent());
            try (OutputStream out = Files.newOutputStream(configPath)) {
                StringBuilder sb = new StringBuilder();
                sb.append("# Velocity multiplier (0.0 to 1.0) when elytra breaks mid-flight\n");
                sb.append(KEY_ELYTRA_BREAK_VELOCITY_MULTIPLIER).append(" = ").append(DEFAULT_ELYTRA_BREAK_VELOCITY_MULTIPLIER).append("\n");
                out.write(sb.toString().getBytes(StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            // Config creation failed
        }
    }
}
