# DataManager

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

DataManager is a simple library plugin for Nukkit Minecraft Bedrock core (and forks), that will help you to create and
manage your SQL connections with ease.

## Libraries

[**Nukkit**](https://github.com/CloudburstMC/Nukkit) is nuclear-powered server software for Minecraft: Pocket Edition
(you can also use PowerNukkit or PowerNukkitX).

[**Sql2o**](https://github.com/aaberg/sql2o) is small useful framework that makes coding for database easy.

[**HikariCP**](https://github.com/brettwooldridge/HikariCP) is a "zero-overhead" production ready JDBC connection pool.
At roughly 130Kb, the library is very light.

## Performance of SELECT

Execute 1000 SELECT statements against a DB and map the data returned to a POJO.

| Method                                   | Duration          |
|------------------------------------------|-------------------|
| Hand coded <code>ResultSet</code>        | 15ms              |
| [Sql2o](https://github.com/aaberg/sql2o) | 24ms (60% slower) |

## How to install

If any plugin requires a DataManager, you just need to download and install it. Usually it will be enough. Also, you can
configure some default database settings in config.yml file, that plugin will create in `plugins` folder.

## Configuration

Default plugin config.yml settings.

```yaml
# sqlite path settings for method SQLiteDatabase(String database)
sqlite:
  # use global folder for saving sqlite tables (near plugins, worlds, etc.) or plugin folder
  global: true
  # name for folder if "global" is set to true
  folder-name: "database"

# mysql settings
mysql:
  # default mysql connection properties
  properties: "useSSL=false&autoReconnect=true&useUnicode=true&serverTimezone=UTC"
  # HikariCP connection pool settings (https://github.com/brettwooldridge/HikariCP)
  hikari:
    auto-commit: true
    connection-timeout: 30000
    idle-timeout: 600000
    keepalive-time: 0
    max-lifetime: 1800000
    maximum-pool-size: 10
```

## How to use

Firstly we recommend to read:

- [*Sql2o Documentation*](https://github.com/aaberg/sql2o/wiki)
- [*HikariCP Configuration*](https://github.com/brettwooldridge/HikariCP#gear-configuration-knobs-baby)

Here is very basic example of your MySQL database class:

```java
import me.hteppl.data.database.MySQLDatabase;
import org.sql2o.Connection;

public class MyDatabase extends MySQLDatabase {

    public MyDatabase() {
        super("host", "database", "user", "password");
        // also you can execute your db scheme with
        this.executeScheme("CREATE TABLE IF NOT EXISTS ...");

        // or use openConnection() method
        try (Connection connection = this.openConnection()) {
            connection.createQuery("SELECT ...").executeUpdate();
        }

        // if you need disable auto commit, use beginTransaction() method
        try (Connection connection = this.beginTransaction()) {
            connection.createQuery("SELECT ...").executeUpdate();
        }
    }
}
```

or SQLite database class:

```java
import me.hteppl.data.database.SQLiteDatabase;
import org.sql2o.Connection;

public class MyDatabase extends SQLiteDatabase {

    public MyDatabase() {
        super("database");
        // also you can execute your db scheme with
        this.executeScheme("CREATE TABLE IF NOT EXISTS ...");

        // or use openConnection() method
        try (Connection connection = this.openConnection()) {
            connection.createQuery("SELECT ...").executeUpdate();
        }

        // if you need disable auto commit, use beginTransaction() method
        try (Connection connection = this.beginTransaction()) {
            connection.createQuery("SELECT ...").executeUpdate();
        }
    }
}
```

After that, you can easily do what you want with your [*Sql2o*](https://www.sql2o.org) connections:

```java
/* import your database class */

public class Main {

    public static void main(String[] args) {
        MyDatabase db = new MyDatabase();

        try (Connection connection = db.openConnection()) {
            connection.createQuery("SELECT ...");
        }

        // also you can execute your db scheme with
        db.executeScheme("CREATE TABLE IF NOT EXISTS ...");
    }
}
```