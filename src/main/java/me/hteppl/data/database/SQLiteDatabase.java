package me.hteppl.data.database;

import me.hteppl.data.DataManager;
import me.hteppl.data.Sql2oDatabase;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.sql.Statement;

public class SQLiteDatabase implements Sql2oDatabase {

    private final Sql2o database;

    private final String dbName;

    public SQLiteDatabase(String dbName) {
        this.dbName = dbName;
        this.database = DataManager.getSQLiteConnection(dbName);
    }

    public SQLiteDatabase(String dbName, String folder) {
        this.dbName = dbName;
        this.database = DataManager.getSQLiteConnection(dbName, folder);
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

    public String getDbName() {
        return dbName;
    }
}
