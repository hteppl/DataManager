package me.hteppl.data;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

public abstract class Sql2oDatabase {

    private final Sql2o sql2o;

    public Sql2oDatabase(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    protected Connection connect() {
        return this.sql2o.open();
    }

    protected void executeScheme(String scheme) {
        if (scheme != null && !scheme.isEmpty()) {
            try (Connection connection = this.connect()) {
                connection.createQuery(scheme).executeUpdate();
            }
        }
    }
}
