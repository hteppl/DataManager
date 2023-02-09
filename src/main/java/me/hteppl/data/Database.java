package me.hteppl.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

@Getter
@RequiredArgsConstructor
public abstract class Database {

    private final Sql2o sql2o;

    public void executeScheme(String scheme) {
        if (scheme != null && !scheme.isEmpty()) {
            try (Connection connection = this.openConnection()) {
                connection.createQuery(scheme).executeUpdate();
            }
        }
    }

    public Connection openConnection() {
        return this.sql2o.open();
    }

    public Connection beginTransaction() {
        return this.sql2o.beginTransaction();
    }

    public Connection beginTransaction(int isolationLevel) {
        return this.sql2o.beginTransaction(isolationLevel);
    }
}
