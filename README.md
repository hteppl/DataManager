# DataManager

DataManager is a simple library plugin Nukkit and PowerNukkit Minecraft Bedrock core, that will help you to create your JDBI
connections with ease (SQLite and MySQL).

[**GitHub Releases**](https://github.com/hteppl/DataManager/releases)

Example project: [DMExample](https://github.com/hteppl/DMExample)

<br>

[**PowerNukkit**](https://github.com/PowerNukkit/PowerNukkit) is a modified version of Nukkit that adds support to a
huge amount of features like water-logging, all new blocks, more plugin events, offhand slot, bug fixes and many more.

[**JDBI**](https://jdbi.org) is small useful framework that makes coding for database easy.

## How to use plugin

If any plugin requires a DataManager you just need to download and install it. Usually it will be enough. Also, you can
configure some database settings in config.yml file, that plugin will create in `plugins` folder.

## How to create your JDBI database

Here is example of your mysql database:

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

or sqlite database:

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
