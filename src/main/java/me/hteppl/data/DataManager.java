package me.hteppl.data;

import cn.nukkit.plugin.PluginBase;
import lombok.Getter;
import me.hteppl.data.utils.Settings;

public class DataManager extends PluginBase {

    @Getter
    private static DataManager instance;
    @Getter
    private static Settings settings;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        instance = this;
        settings = new Settings(this.getConfig());
    }
}
