package me.hteppl.data.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import me.hteppl.data.DataManager;
import org.sql2o.Sql2o;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Create {

    public static Sql2o createSQLite(String database) {
        Settings.SQLiteSettings settings = DataManager.getSettings().getSqlite();
        return createSQLite(
                settings.isGlobal()
                        ? settings.getFolderName()
                        : DataManager.getInstance().getDataFolder().getPath(),
                database
        );
    }

    public static Sql2o createSQLite(String folder, String database) {
        try {
            Class.forName("org.sqlite.JDBC");
            Files.createDirectories(Paths.get(folder));
        } catch (IOException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        return new Sql2o("jdbc:sqlite:" + folder + "/" + database + ".db", null, null);
    }

    public static Sql2o createMySQL(String host, int port, String database, String user, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        HikariConfig config = new HikariConfig();
        Settings.MySQLSettings settings = DataManager.getSettings().getMysql();
        Settings.HikariSettings hikari = settings.getHikari();

        config.setJdbcUrl("jdbc:mysql://" + host + ":" + port + "/" + database + "?" + settings.getProperties());
        config.setUsername(user);
        config.setPassword(password);
        config.setAutoCommit(hikari.autoCommit);
        config.setConnectionTimeout(hikari.connectionTimeout);
        config.setIdleTimeout(hikari.idleTimeout);
        config.setKeepaliveTime(hikari.keepaliveTime);
        config.setMaxLifetime(hikari.maxLifetime);
        config.setMaximumPoolSize(hikari.maximumPoolSize);

        return createMySQL(new HikariDataSource(config));
    }

    public static Sql2o createMySQL(DataSource ds) {
        return new Sql2o(ds);
    }
}
