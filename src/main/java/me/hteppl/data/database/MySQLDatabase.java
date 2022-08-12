package me.hteppl.data.database;

import me.hteppl.data.DataManager;
import me.hteppl.data.Database;
import org.jdbi.v3.core.Jdbi;

public class MySQLDatabase extends Database {

    public MySQLDatabase(String host, String database, String user, String password) {
        this(host, 3306, database, user, password);
    }

    public MySQLDatabase(String host, int port, String database, String user, String password) {
        super(Jdbi.create("jdbc:mysql://" + host + ":" + port + "/" + database + "?" + DataManager.getMysqlProperties(), user, password));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException exception) {
            throw new RuntimeException(exception);
        }

        this.executeScheme("CREATE DATABASE IF NOT EXISTS " + database);
    }
}
