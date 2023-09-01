package me.hteppl.data.database;

import me.hteppl.data.utils.Create;

import javax.sql.DataSource;

public class SQLiteDatabase extends Database {

    public SQLiteDatabase(String database) {
        super(Create.createSQLite(database));
    }

    public SQLiteDatabase(String folder, String database) {
        super(Create.createSQLite(folder, database));
    }

    public SQLiteDatabase(DataSource ds) {
        super(Create.createByDataSource(ds));
    }
}
