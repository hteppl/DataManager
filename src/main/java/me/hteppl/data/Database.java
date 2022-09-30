package me.hteppl.data;

import lombok.Getter;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public abstract class Database {

    @Getter
    protected final Connection connection;
    @Getter
    private final Sql2o sql2o;

    public Database(Sql2o sql2o) {
        this.sql2o = sql2o;
        this.connection = sql2o.open();
    }

    protected void executeScheme(String scheme) {
        if (scheme != null && !scheme.isEmpty()) {
            this.connection.createQuery(scheme).executeUpdate();
        }
    }
}
