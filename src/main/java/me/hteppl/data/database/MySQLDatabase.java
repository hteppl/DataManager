package me.hteppl.data.database;

import me.hteppl.data.utils.Create;

import javax.sql.DataSource;

public class MySQLDatabase extends Database {

    public MySQLDatabase(String host, String database, String user, String password) {
        this(host, 3306, database, user, password);
    }

    public MySQLDatabase(String host, int port, String database, String user, String password) {
        super(Create.createMySQL(host, port, database, user, password));
    }

    public MySQLDatabase(String host, int port, String database, String user, String password, String properties) {
        super(Create.createMySQL(host, port, database, user, password, properties));
    }

    public MySQLDatabase(DataSource ds) {
        super(Create.createByDataSource(ds));
    }
}
