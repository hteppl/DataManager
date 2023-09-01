package me.hteppl.data.database;

import lombok.Getter;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.BeanMapper;
import org.jdbi.v3.core.mapper.reflect.ConstructorMapper;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class Database {

    private final Jdbi jdbi;

    public Database(Jdbi jdbi) {
        this.jdbi = jdbi;
        this.registerRowMappers();
        this.registerConstructorMappers();
    }

    public Handle getHandle() {
        return jdbi.open();
    }

    private void registerRowMappers() {
        List<Class<?>> mappers = new ArrayList<>();
        this.getRowMappers(mappers);
        for (Class<?> aClass : mappers) {
            jdbi.registerRowMapper(BeanMapper.factory(aClass));
        }
    }

    private void registerConstructorMappers() {
        List<Class<?>> mappers = new ArrayList<>();
        this.getConstructorMappers(mappers);
        for (Class<?> aClass : mappers) {
            jdbi.registerRowMapper(ConstructorMapper.factory(aClass));
        }
    }

    protected void getRowMappers(List<Class<?>> classList) {
    }

    protected void getConstructorMappers(List<Class<?>> classList) {
    }
}
