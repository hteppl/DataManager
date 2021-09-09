package me.hteppl.data;

import org.sql2o.Connection;

public interface Sql2oDatabase {

    Connection getConnection();

    void executeScheme(String scheme);

    String getDbName();

}
