package me.hteppl.data.database;

import me.hteppl.data.DataManager;
import me.hteppl.data.Database;
import org.sql2o.Sql2o;

public class MySQLDatabase extends Database {

    public MySQLDatabase(String host, String database, String user, String password) {
        this(host, 3306, database, user, password);
    }

    public MySQLDatabase(String host, int port, String database, String user, String password) {
        super(MySQLDatabase.createSql2o(host, port, database, user, password));

        this.executeScheme("CREATE DATABASE IF NOT EXISTS " + database);
    }

    private static Sql2o createSql2o(String host, int port, String database, String user, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException exception) {
            throw new RuntimeException(exception);
        }

        return new Sql2o("jdbc:mysql://" + host + ":" + port + "/" + database + "?" + DataManager.getMysqlProperties(), user, password);
    }
}
