package me.hteppl.data.utils;

import cn.nukkit.utils.Config;
import cn.nukkit.utils.ConfigSection;
import lombok.Getter;

@Getter
public class Settings {

    private final String sqliteDirectory;
    private final String mysqlProperties;
    private final HikariSettings hikari;

    public Settings(Config config, String pluginPath) {
        String dir = config.getString("sqlite-directory", "database");
        this.sqliteDirectory = dir.trim().isEmpty() ? pluginPath : dir;
        this.mysqlProperties = config.getString("mysql-properties");
        this.hikari = new HikariSettings(config.getSection("hikari"));
    }

    @Getter
    public static class HikariSettings {

        public final boolean autoCommit;
        public final int connectionTimeout;
        public final int idleTimeout;
        public final int keepaliveTime;
        public final int maxLifetime;
        public final int maximumPoolSize;

        public HikariSettings(ConfigSection section) {
            this.autoCommit = section.getBoolean("auto-commit", true);
            this.connectionTimeout = section.getInt("connection-timeout", 30000);
            this.idleTimeout = section.getInt("idle-timeout", 600000);
            this.keepaliveTime = section.getInt("keepalive-time", 0);
            this.maxLifetime = section.getInt("max-lifetime", 1800000);
            this.maximumPoolSize = section.getInt("maximum-pool-size", 10);
        }
    }
}
