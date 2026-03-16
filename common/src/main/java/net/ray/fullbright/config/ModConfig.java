package net.ray.fullbright.config;

import java.io.*;
import java.util.Properties;

public class ModConfig {
    private static final Properties props = new Properties();
    private static final File configFile = new File("config/optimized_fullbright.properties");

    public static boolean enableFullbright = true;
    public static boolean debugMode = false;

    public static void load() {
        try {
            if (configFile.exists()) {
                try (FileInputStream in = new FileInputStream(configFile)) {
                    props.load(in);

                    enableFullbright = Boolean.parseBoolean(props.getProperty("enableFullbright", "true"));
                    debugMode = Boolean.parseBoolean(props.getProperty("debugMode", "false"));
                }
            } else {
                save();
            }
        } catch (IOException e) {
            System.out.println("Failed to load config: " + e.getMessage());
        }
    }

    public static void save() {
        try {
            configFile.getParentFile().mkdirs();

            props.setProperty("enableFullbright", String.valueOf(enableFullbright));
            props.setProperty("debugMode", String.valueOf(debugMode));

            try (FileOutputStream out = new FileOutputStream(configFile)) {
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(out));
                writer.println("#Optimized Fullbright Config");
                writer.println("enableFullbright=" + enableFullbright);
                writer.println("");
                writer.println("#DO NOT ENABLE");
                writer.println("#This is purely for testing purposes");
                writer.println("debugMode=" + debugMode);
                writer.flush();
            }
        } catch (IOException e) {
            System.out.println("Failed to save config: " + e.getMessage());
        }
    }
}