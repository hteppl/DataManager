package me.hteppl.data;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;

public class DataManager extends PluginBase {

    private static String sqliteFolder, mysqlProperties;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        Config config = this.getConfig();
        sqliteFolder = config.getString("sqlite-folder");
        mysqlProperties = config.getString("mysql-properties");
    }

    public static String getSqliteFolder() {
        return sqliteFolder;
    }

    public static String getMysqlProperties() {
        return mysqlProperties;
    }
}
