## Spring Data R2DBC

### R2DBC (https://spring.io/projects/spring-data-r2dbc)

* Reactive Relational Database Connectivity

### 支持的数据库

* **H2 (io.r2dbc:r2dbc-h2)** <br><br/>
* **MariaDB (org.mariadb:r2dbc-mariadb)** <br><br/>
* **Microsoft SQL Server (io.r2dbc:r2dbc-mssql)** <br><br/>
* M**ySQL (dev.miku:r2dbc-mysql)** <br><br/>
* **jasync-sql MySQL (com.github.jasync-sql:jasync-r2dbc-mysql)** <br><br/>
* **Postgres (io.r2dbc:r2dbc-postgresql)** <br><br/>
* O**racle (com.oracle.database.r2dbc:oracle-r2dbc)**

***

### 一些主要的类

* **ConnectionFactory** <br><br/>
* **DatabaseClient**
    * **sql(SQL)** <br><br/>

* **R2dbcEntityTemplate**
  * **select(Class<T> domainType)** <br><br/>

* **R2dbcExceptionTranslator**
    * **SqlErrorCodeR2dbcExceptionTranslator**


***

## 参考链接
1. [https://spring.io/projects/spring-data-r2dbc](https://spring.io/projects/spring-data-r2dbc)
2. [https://docs.spring.io/spring-data/r2dbc/docs/current/reference/html/](https://docs.spring.io/spring-data/r2dbc/docs/current/reference/html/)
3. [https://projectreactor.io/docs/core/release/reference/#metrics](https://projectreactor.io/docs/core/release/reference/#metrics)
4. [https://stackoverflow.com/questions/64355106/setup-h2-in-spring-boot-application-with-r2dbc-and-flyway](https://stackoverflow.com/questions/64355106/setup-h2-in-spring-boot-application-with-r2dbc-and-flyway)
5. [https://docs.liquibase.com/tools-integrations/springboot/springboot.html](https://docs.liquibase.com/tools-integrations/springboot/springboot.html)
6. [https://www.vinsguru.com/spring-data-r2dbc-query-by-example/](https://www.vinsguru.com/spring-data-r2dbc-query-by-example/)