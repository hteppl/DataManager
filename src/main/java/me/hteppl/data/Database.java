package me.hteppl.data;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

public abstract class Database {

    private final Jdbi jdbi;

    public Database(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    protected Handle connect() {
        return this.jdbi.open();
    }

    protected void executeScheme(String scheme) {
        if (scheme != null && !scheme.isEmpty()) {
            try (Handle handle = this.connect()) {
                handle.createUpdate(scheme).execute();
            }
        }
    }
}
