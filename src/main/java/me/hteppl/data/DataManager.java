package me.hteppl.data;

import cn.nukkit.plugin.PluginBase;
import lombok.Getter;
import me.hteppl.data.utils.Settings;

public class DataManager extends PluginBase {

    @Getter
    private static Settings settings;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        settings = new Settings(this.getConfig(), this.getDataFolder().getPath());
    }
}
