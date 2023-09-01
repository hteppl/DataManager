package me.hteppl.data.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.log4j.Log4j2;
import me.hteppl.data.DataManager;
import org.jdbi.v3.core.Jdbi;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Log4j2
public class Create {

    public static Jdbi createSQLite(String database) {
        return createSQLite(DataManager.getSettings().getSqliteDirectory(), database);
    }

    public static Jdbi createSQLite(String folder, String database) {
        try {
            Class.forName("org.sqlite.JDBC");
            Files.createDirectories(Paths.get(folder));
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Error while trying to load SQLite driver", ex);
        } catch (IOException ex) {
            throw new RuntimeException("Error while trying to create " + folder, ex);
        }

        return Jdbi.create("jdbc:sqlite:" + folder + "/" + database + ".db");
    }

    public static Jdbi createMySQL(String host, int port, String database, String user, String password) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Error while trying to load MariaDB driver", ex);
        }

        try {
            var settings = DataManager.getSettings();
            var hikari = settings.getHikari();
            var config = new HikariConfig();

            String properties = settings.getMysqlProperties();
            properties = properties != null && !properties.trim().isEmpty() ? "?" + properties : "";

            config.setJdbcUrl("jdbc:mariadb://" + host + ":" + port + "/" + database + properties);
            config.setUsername(user);
            config.setPassword(password);
            config.setAutoCommit(hikari.autoCommit);
            config.setConnectionTimeout(hikari.connectionTimeout);
            config.setIdleTimeout(hikari.idleTimeout);
            config.setKeepaliveTime(hikari.keepaliveTime);
            config.setMaxLifetime(hikari.maxLifetime);
            config.setMaximumPoolSize(hikari.maximumPoolSize);

            return createByDataSource(new HikariDataSource(config));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Jdbi createByDataSource(DataSource ds) {
        return Jdbi.create(ds);
    }
}
