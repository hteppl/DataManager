package me.hteppl.data.utils;

import cn.nukkit.utils.Config;
import cn.nukkit.utils.ConfigSection;
import lombok.Getter;

@Getter
public class Settings {

    private final SQLiteSettings sqlite;
    private final MySQLSettings mysql;

    public Settings(Config config) {
        this.sqlite = new SQLiteSettings(config.getSection("sqlite"));
        this.mysql = new MySQLSettings(config.getSection("mysql"));
    }

    @Getter
    public static class SQLiteSettings {

        private final boolean global;
        private final String folderName;

        public SQLiteSettings(ConfigSection section) {
            this.global = section.getBoolean("global", true);
            this.folderName = section.getString("folder-name", "database");
        }
    }

    @Getter
    public static class MySQLSettings {

        private final String properties;
        private final HikariSettings hikari;

        public MySQLSettings(ConfigSection section) {
            this.properties = section.getString("properties");
            this.hikari = new HikariSettings(section.getSection("hikari"));
        }
    }

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
