package me.hteppl.data.database;

import me.hteppl.data.Sql2oDatabase;
import me.hteppl.data.util.Utils;

public class MySQLDatabase extends Sql2oDatabase {

    public MySQLDatabase(String host, String database, String user, String password) {
        this(host, 3306, database, user, password);
    }

    public MySQLDatabase(String host, int port, String database, String user, String password) {
        super(Utils.createSql2oForMySQL(host, port, database, user, password));

        this.executeScheme("CREATE DATABASE IF NOT EXISTS " + database);
    }
}
