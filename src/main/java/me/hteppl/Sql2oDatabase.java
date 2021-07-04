package me.hteppl;

import org.sql2o.Connection;

public interface Sql2oDatabase {

    Connection getConnection();

}
