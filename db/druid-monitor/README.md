## 慢SQL日志
### 系统属性配置
* **druid.stat.logSlowSql=true**
* **druid.stat.slowSqlMillis=3000**

### Spring Boot
* **spring.datasource.druid.filter.stat.enable=true**
* **spring.datasource.druid.filter.stat.log-slow-sql=true**
* **spring.datasource.druid.filter.stat.slow-sql-millis=3000**

***

## 注意事项
* **没有特殊情况，不要在生产环境打开监控的Servlet**
* **没有链接泄露可能的情况下，不要开启removeAbandoned**
* **testXxx 的使用需要注意**
* **务必配置合理的超时时间**

***

## 参考链接
[https://blog.csdn.net/qq_32370913/article/details/105924209](https://blog.csdn.net/qq_32370913/article/details/105924209)