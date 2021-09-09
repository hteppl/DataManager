package me.hteppl.data.database;

import me.hteppl.data.DataManager;
import me.hteppl.data.Sql2oDatabase;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.sql.Statement;

public class MySQLDatabase implements Sql2oDatabase {

    private final Sql2o database;

    private Connection connection;

    private final String dbName;

    public MySQLDatabase(String database, String host, String user, String password) {
        this.dbName = database;
        this.database = DataManager.getMySQLConnection(database, host, user, password);
        this.connection = this.database.open();
    }

    public Connection getConnection() {
        try {
            if (!this.connection.getJdbcConnection().isValid(DataManager.mysqlValidation)) {
                this.connection.close();
                this.connection = this.database.open();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return this.connection;
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

    public Sql2o getDatabase() {
        return this.database;
    }

    public String getDbName() {
        return dbName;
    }
}
