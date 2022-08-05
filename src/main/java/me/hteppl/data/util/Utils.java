package me.hteppl.data.util;

import me.hteppl.data.DataManager;
import org.sql2o.Sql2o;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utils {

    public static Sql2o createSql2oForMySQL(String host, int port, String database, String user, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = host + ":" + port + "/" + database + "?" + DataManager.mysqlProperties;
            return new Sql2o("jdbc:mysql://" + url, user, password);
        } catch (ClassNotFoundException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static Sql2o createSql2oForSQLite(String folder, String database) {
        try {
            Class.forName("org.sqlite.JDBC");

            try {
                Files.createDirectories(Paths.get(folder));
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }

            return new Sql2o("jdbc:sqlite:" + folder + "/" + database + ".db", null, null);
        } catch (ClassNotFoundException exception) {
            throw new RuntimeException(exception);
        }
    }
}
