# DataManager

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![jitpack](https://jitpack.io/v/hteppl/DataManager.svg)](https://jitpack.io/#hteppl/DataManager)

DataManager is a simple library plugin for [PowerNukkitX](https://github.com/PowerNukkitX/PowerNukkitX) Minecraft
Bedrock core, that will help you to create and
manage your SQL connections with ease.

## Build JAR File

```shell
$ git clone https://github.com/hteppl/DataManager
$ cd DataManager
$ mvn clean package
```

## How to install

If any plugin requires a DataManager, you just need to download and put it in `plugins` folder. Usually it will be
enough. Also, you can configure some default database settings in `config.yml`.

### Maven

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

```xml
<dependency>
    <groupId>com.github.hteppl</groupId>
    <artifactId>DataManager</artifactId>
    <version>2.2.0-SNAPSHOT</version>
</dependency>
```

### Gradle

```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

```groovy
dependencies {
    implementation 'com.github.hteppl:DataManager:2.2.0-SNAPSHOT'
}
```

## Configuration

Default plugin `config.yml` settings.

```yaml
# sqlite directory name for anonymous databases
# set empty, for creating database file in plugin's folder by default
sqlite-directory: "database"

# default mysql connection properties
mysql-properties: "useSSL=false&autoReconnect=true&useUnicode=true&serverTimezone=UTC"

# Hikari connection pool settings (https://github.com/brettwooldridge/HikariCP)
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

- [*Jdbi Developer Guide*](http://jdbi.org/)
- [*HikariCP Configuration*](https://github.com/brettwooldridge/HikariCP#gear-configuration-knobs-baby)

Very basic example of MySQL database class:

```java
import me.hteppl.data.database.MySQLDatabase;
import org.jdbi.v3.core.Handle;

public class MyDatabase extends MySQLDatabase {

    public MyDatabase() {
        super("host", "database", "user", "password");

        try (Handle handle = this.getHandle()) {
            handle.createUpdate("...")
                    .bind("var1", "data1")
                    .bind("var2", "data2")
                    .execute();
        }
    }
}
```

Example of SQLite database class:

```java
import me.hteppl.data.database.SQLiteDatabase;
import org.jdbi.v3.core.Handle;

public class MyDatabase extends SQLiteDatabase {

    public MyDatabase() {
        super("database");

        try (Handle handle = this.getHandle()) {
            handle.createUpdate("...")
                    .bind("var1", "data1")
                    .bind("var2", "data2")
                    .execute();
        }
    }
}
```

After that, you can easily do what you want with your [*Jdbi*](http://jdbi.org/) handles:

```java
/* import your database class */

public class Main {

    public static void main(String[] args) {
        MyDatabase database = new MyDatabase();

        try (Handle handle = database.getHandle()) {
            handle.createUpdate("...")
                    .bind("var1", "data1")
                    .bind("var2", "data2")
                    .execute();
        }
    }
}
```

## Libraries

[**PowerNukkitX**](https://github.com/PowerNukkitX/PowerNukkitX) is a branch version based on PowerNukkit,
developed and maintained by PowerNukkitX.

[**MariaDB Connector**](https://github.com/mariadb-corporation/mariadb-connector-j) MariaDB Connector/J is a Type 4 JDBC
driver. It was developed specifically as a lightweight JDBC connector for use with MariaDB and MySQL database servers.

[**Jdbi**](https://github.com/jdbi/jdbi) The Jdbi library provides convenient, idiomatic access to relational databases
in Java.

[**HikariCP**](https://github.com/brettwooldridge/HikariCP) is a "zero-overhead" production ready JDBC connection pool.
At roughly 130Kb, the library is very light.

## License

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT)