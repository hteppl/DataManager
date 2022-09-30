# DataManager

DataManager is a simple library plugin for PowerNukkit Minecraft Bedrock core, that will help you to create your Sql2o
connections with ease (SQLite and MySQL).

[**GitHub Releases**](https://github.com/hteppl/DataManager/releases)

Example project: [DMExample](https://github.com/hteppl/DMExample)

<br>

[**PowerNukkit**](https://github.com/PowerNukkit/PowerNukkit) is a modified version of Nukkit that adds support to a
huge amount of features like water-logging, all new blocks, more plugin events, offhand slot, bug fixes and many more.

[**Sql2o**](https://www.sql2o.org) is small useful framework that makes coding for database easy.

## Performance of SELECT

Execute 1000 SELECT statements against a DB and map the data returned to a POJO.

| Method                                   | Duration          |
|------------------------------------------|-------------------|
| Hand coded <code>ResultSet</code>        | 15ms              |
| [Sql2o](https://github.com/aaberg/sql2o) | 24ms (60% slower) |
| [JDBI](https://github.com/jdbi/jdbi)     | 26ms (73% slower) |

## How to use plugin

If any plugin requires a DataManager you just need to download and install it. Usually it will be enough. Also, you can
configure some database settings in config.yml file, that plugin will create in `plugins` folder.

## How to create your Sql2o database

Firstly we recommend to read [*Sql2o Documentation*](https://www.sql2o.org)

Here is very basic example of your MySQL database class:

```java
import me.hteppl.data.database.MySQLDatabase;

public class MyDatabase extends MySQLDatabase {

    public MyDatabase() {
        super("host", "database", "user", "password");
        // also you can execute your db scheme with 
        // this.executeScheme("scheme");
    }
}
```

or SQLite database class:

```java
import me.hteppl.data.database.SQLiteDatabase;

public class MyDatabase extends SQLiteDatabase {

    public MyDatabase() {
        super("database");
        // also you can execute your db scheme with
        // this.executeScheme("scheme");
    }
}
```

After that, you can easily do what you want with your [*Sql2o*](https://www.sql2o.org) connections

```java
import me.hteppl.data.Database;
/* import your database class */

public class Main {

    public static void main(String[] args) {
        Database db = new MyDatabase();
        db.getConnection().createQuery(...);
    }
}
```