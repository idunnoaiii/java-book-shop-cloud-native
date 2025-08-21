# Database Connection Strings Reference

## Pattern Recognition

**JDBC URLs follow**: `jdbc:[database]://[host]:[port]/[database]?params`
**URI style**: `[database]://[user]:[pass]@[host]:[port]/[database]`
**Key-value style**: `Key1=value1;Key2=value2;`

## Memory Tricks

### SQL Server (Port 1433)
- **JDBC**: `jdbc:sqlserver://localhost:1433;databaseName=mydb`
- **Key-Value**: `Server=localhost;Database=mydb;User Id=user;Password=pass;`
- *Remember*: "SQL Server = 1433, uses semicolons"

### PostgreSQL (Port 5432) 
- **JDBC**: `jdbc:postgresql://localhost:5432/mydb`
- **URI**: `postgresql://user:pass@localhost:5432/mydb`
- *Remember*: "Postgres = 5432, clean syntax"

### MySQL (Port 3306)
- **JDBC**: `jdbc:mysql://localhost:3306/mydb`
- **URI**: `mysql://user:pass@localhost:3306/mydb`
- *Remember*: "MySQL = 3306, similar to Postgres"

### MongoDB (Port 27017)
- **URI**: `mongodb://user:pass@localhost:27017/mydb`
- **Cloud**: `mongodb+srv://user:pass@cluster.net/mydb`
- *Remember*: "Mongo = 27017, always URI style, +srv for cloud"

## Quick Interview Format
```
SQL Server: jdbc:sqlserver://localhost:1433;databaseName=db
PostgreSQL: jdbc:postgresql://localhost:5432/db
MySQL: jdbc:mysql://localhost:3306/db
MongoDB: mongodb://localhost:27017/db
```

Practice writing these 5 times and you'll have them memorized!

## Complete Examples

### SQL Server (MSSQL)
```
Server=localhost;Database=mydb;User Id=username;Password=password;
Server=localhost,1433;Database=mydb;Integrated Security=true;
jdbc:sqlserver://localhost:1433;databaseName=mydb;user=username;password=password
```

### PostgreSQL
```
Host=localhost;Database=mydb;Username=username;Password=password;Port=5432;
postgresql://username:password@localhost:5432/mydb
jdbc:postgresql://localhost:5432/mydb?user=username&password=password
```

### MySQL
```
Server=localhost;Database=mydb;Uid=username;Pwd=password;Port=3306;
mysql://username:password@localhost:3306/mydb
jdbc:mysql://localhost:3306/mydb?user=username&password=password
```

### MongoDB
```
mongodb://username:password@localhost:27017/mydb
mongodb://localhost:27017/mydb
mongodb+srv://username:password@cluster.mongodb.net/mydb
```