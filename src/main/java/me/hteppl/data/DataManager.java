package me.hteppl.data;

import cn.nukkit.Server;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import org.sql2o.Sql2o;

import java.io.File;

public class DataManager extends PluginBase {

    private static String sqliteFolder;

    private static String mysqlFlags;

    public static int mysqlValidation;

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        Config config = this.getConfig();

        sqliteFolder = Server.getInstance().getFilePath() + config.getString("sqlite");
        mysqlFlags = config.getString("mysql-flags");
        mysqlValidation = config.getInt("mysql-validation");

        new File(sqliteFolder).mkdirs();
    }

    public static Sql2o getMySQLConnection(String database, String host, String user, String password) {
        return getMySQLConnection(database, host, 3306, user, password);
    }

    public static Sql2o getMySQLConnection(String database, String host, int port, String user, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            StringBuilder url = new StringBuilder(host);
            if (port >= 0) url.append(":").append(port);
            url.append("/").append(database)
                    .append("?").append(mysqlFlags);

            return new Sql2o("jdbc:mysql://" + url, user, password);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return null;
    }

    public static Sql2o getSQLiteConnection(String database) {
        return getSQLiteConnection(database, sqliteFolder);
    }

    public static Sql2o getSQLiteConnection(String database, String folder) {
        try {
            Class.forName("org.sqlite.JDBC");
            return new Sql2o("jdbc:sqlite:" + folder + "/" + database + ".db", null, null);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return null;
    }
}