package me.hteppl.data;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import lombok.Getter;

public class DataManager extends PluginBase {

    @Getter
    private static String sqliteFolder, mysqlProperties;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        Config config = this.getConfig();
        sqliteFolder = config.getString("sqlite-folder");
        mysqlProperties = config.getString("mysql-properties");
    }
}
