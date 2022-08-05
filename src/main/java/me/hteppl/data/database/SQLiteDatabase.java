package me.hteppl.data.database;

import me.hteppl.data.Sql2oDatabase;
import me.hteppl.data.util.Utils;

public class SQLiteDatabase extends Sql2oDatabase {

    public SQLiteDatabase(String folder, String database) {
        super(Utils.createSql2oForSQLite(folder, database));
    }
}
