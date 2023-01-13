package me.hteppl.data.database;

import me.hteppl.data.DataManager;
import me.hteppl.data.Database;
import org.sql2o.Sql2o;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SQLiteDatabase extends Database {

    public SQLiteDatabase(String database) {
        this(DataManager.getSqliteFolder(), database);
    }

    public SQLiteDatabase(String folder, String database) {
        super(SQLiteDatabase.createSql2o(folder, database));
    }

    private static Sql2o createSql2o(String folder, String database) {
        try {
            Class.forName("org.sqlite.JDBC");

            Files.createDirectories(Paths.get(folder));
        } catch (IOException | ClassNotFoundException exception) {
            throw new RuntimeException(exception);
        }

        return new Sql2o("jdbc:sqlite:" + folder + "/" + database + ".db", null, null);
    }
}
