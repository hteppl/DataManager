package me.hteppl.data.database;

import me.hteppl.data.DataManager;
import me.hteppl.data.Database;
import org.jdbi.v3.core.Jdbi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SQLiteDatabase extends Database {

    public SQLiteDatabase(String database) {
        this(DataManager.getSqliteFolder(), database);
    }

    public SQLiteDatabase(String folder, String database) {
        super(Jdbi.create("jdbc:sqlite:" + folder + "/" + database + ".db"));

        try {
            Class.forName("org.sqlite.JDBC");

            Files.createDirectories(Paths.get(folder));
        } catch (IOException | ClassNotFoundException exception) {
            throw new RuntimeException(exception);
        }
    }
}
