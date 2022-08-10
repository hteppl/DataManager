package me.hteppl.data;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;

public class DataManager extends PluginBase {

    public static String mysqlProperties;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        Config config = this.getConfig();
        mysqlProperties = config.getString("mysql-properties");
    }
}
