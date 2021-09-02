

# DataManager
DataManager is a simple library plugin for PowerNukkit Minecraft Bedrock core, that will help you to create your sql2o connections with ease (SQLite and MySQL).

[**Github Releases**](https://github.com/hteppl/DataManager/releases)

Example project: [DMExample](https://github.com/hteppl/DMExample)

<br>

[**PowerNukkit**](https://github.com/PowerNukkit/PowerNukkit) is a modified version of Nukkit that adds support to a huge amount of features like water-logging, all new blocks, more plugin events, offhand slot, bug fixes and many more.

[**Sql2o**](http://www.sql2o.org/) is small useful framework that makes coding for database easy.
## How to use plugin

If any plugin requires a DataManager you just need to download and install it. Usually it will be enough. Also you can configure some database settings in config.yml file, that plugin will create in `plugins` folder.

## How to create your sql2o database

Here is example of your mysql database:
```java
import me.hteppl.data.database.MySQLDatabase;  
  
public class MyDatabase extends MySQLDatabase {  
  
    public MyDatabase() {  
        super("database", "127.0.0.1", "root", "root");  
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
