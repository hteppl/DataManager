package me.hteppl.data;

import lombok.Getter;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

public abstract class Database {

    @Getter
    private final Jdbi jdbi;
    private Handle handle;

    public Database(Jdbi jdbi) {
        this.jdbi = jdbi;
        this.connect();
    }

    protected void connect() {
        this.handle = this.jdbi.open();
    }

    public Handle getHandle() {
        if (this.handle.isClosed()) this.connect();
        return this.handle;
    }

    protected void executeScheme(String scheme) {
        if (scheme != null && !scheme.isEmpty()) {
            this.getHandle().createUpdate(scheme).execute();
        }
    }
}
