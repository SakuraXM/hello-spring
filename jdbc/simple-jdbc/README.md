## Spring JDBC

* **core, JdbcTemplate等相关核心接口和类**
* **datasource, 数据源相关的辅助类**
* **object, 将基本的JDBC操作封装成对象**
* **support, 错误码等其它辅助工具**

***

## 常用的Bean注解
* @Component
* @Repository
* @Service
* @Controller
  * RestController

***

## 简单的JDBC操作
JdbcTemplate
* query
* queryForObject
* queryForList
* update
* execute
* call

***

## NamedParameterJdbcTemplate
`在经典的 JDBC 用法中, SQL 参数是用占位符 ? 表示,并且受到位置的限制. 定位参数的问题在于, 一旦参数的顺序发生变化, 就必须改变参数绑定.
在 Spring JDBC 框架中, 绑定 SQL 参数的另一种选择是使用具名参数(named parameter)`

#### 具名参数
**SQL 按名称(以冒号开头)而不是按位置进行指定. 具名参数更易于维护, 也提升了可读性. 具名参数由框架类在运行时用占位符取代
具名参数只在 NamedParameterJdbcTemplate 中得到支持**

***

## SQL批处理
**JdbcTemplate**
* batchUpdate
  * BatchPreparedStatementSetter

**NamedParameterJdbcTemplate**
* batchUpdate
  * SqlParameterSourceUtils.createBatch
