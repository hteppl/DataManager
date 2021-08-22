package me.hteppl.database;

import me.hteppl.DataManager;
import me.hteppl.Sql2oDatabase;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.sql.Statement;

public class SQLiteDatabase implements Sql2oDatabase {

    private final Sql2o database;

    public SQLiteDatabase(String dbName) {
        this(dbName, null);
    }

    public SQLiteDatabase(String dbName, String scheme) {
        this.database = DataManager.getSQLiteConnection(dbName);
        this.executeScheme(scheme);
    }

    public void executeScheme(String scheme) {
        if (scheme != null) {
            try (java.sql.Connection connection = this.getConnection().getJdbcConnection(); Statement statement = connection.createStatement()) {
                statement.executeUpdate(scheme);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        return this.database.open();
    }

    public Sql2o getDatabase() {
        return this.database;
    }
}
