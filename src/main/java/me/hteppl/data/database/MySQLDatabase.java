package me.hteppl.data.database;

import me.hteppl.data.DataManager;
import me.hteppl.data.Sql2oDatabase;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class MySQLDatabase implements Sql2oDatabase {

    private final Sql2o database;

    private Connection connection;

    public MySQLDatabase(String database, String host, String user, String password) {
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

    public Sql2o getDatabase() {
        return this.database;
    }
}
