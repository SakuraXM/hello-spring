## MyBatis Generator
### MyBatis Generator（http://www.mybatis.org/generator/）
* **MyBatis代码生成器**<br></br>
* **根据数据库表生成相关代码**<br></br>
  * **POJO**<br></br>
  * **Mapper接口**<br></br>
  * **SQL Map XML**<br></br>

### example MBG configuration file
```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
  PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
  "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">

<generatorConfiguration>
  <classPathEntry location="/Program Files/IBM/SQLLIB/java/db2java.zip" />

  <context id="DB2Tables" targetRuntime="MyBatis3">
    <jdbcConnection driverClass="COM.ibm.db2.jdbc.app.DB2Driver"
        connectionURL="jdbc:db2:TEST"
        userId="db2admin"
        password="db2admin">
    </jdbcConnection>

    <javaTypeResolver >
      <property name="forceBigDecimals" value="false" />
    </javaTypeResolver>

    <javaModelGenerator targetPackage="test.model" targetProject="\MBGTestProject\src">
      <property name="enableSubPackages" value="true" />
      <property name="trimStrings" value="true" />
    </javaModelGenerator>

    <sqlMapGenerator targetPackage="test.xml"  targetProject="\MBGTestProject\src">
      <property name="enableSubPackages" value="true" />
    </sqlMapGenerator>

    <javaClientGenerator type="XMLMAPPER" targetPackage="test.dao"  targetProject="\MBGTestProject\src">
      <property name="enableSubPackages" value="true" />
    </javaClientGenerator>

    <table schema="DB2ADMIN" tableName="ALLTYPES" domainObjectName="Customer" >
      <property name="useActualColumnNames" value="true"/>
      <generatedKey column="ID" sqlStatement="DB2" identity="true" />
      <columnOverride column="DATE_FIELD" property="startDate" />
      <ignoreColumn column="FRED" />
      <columnOverride column="LONG_VARCHAR_FIELD" jdbcType="VARCHAR" />
    </table>

  </context>
</generatorConfiguration>
```

***


## MyBatis Generator使用
### 命令行
* **java -jar mybatis-generator-core-x.x.x.jar -configfile generatorConfig.xml**<br></br>

### Maven Plugin（mybatis-generator-maven-plugin）
* **mvn mybatis-generator:generate**<br></br>
* **${basedir}/src/main/resources/generatorConfig.xml**<br></br>

### Eclipse Plugin

### Java程序

### Ant Task


***


## 配置MyBatis Generator
### generatorConfiguration
### context
* **jdbcConnection**<br></br>
* **javaModelGenerator**<br></br>
* **sqlMapGenerator**<br></br>
* **javaClientGenerator (ANNOTATEDMAPPER / XMLMAPPER / MIXEDMAPPER)**<br></br>
* **table**<br></br>


***


## Plugins
### 内置插件都在 org.mybatis.generator.plugins 包中
* **FluentBuilderMethodPlugin**<br></br>
* **ToStringPlugin**<br></br>
* **SerializablePlugin**<br></br>
* **RowBoundsPlugin**<br></br>
* ......


***

## 问题
### Spring Boot 2.7.0 生成不了，改为低版本可以生成
### 多个项目 targetProject 路径问题
### 自动生成和手动修改的文件分别放置，使用手动修改的文件


***
## 参考链接
1. [http://www.mybatis.org/generator/](http://www.mybatis.org/generator/)
2. [https://www.cnblogs.com/xuxiaobai13/p/11846772.html](https://www.cnblogs.com/xuxiaobai13/p/11846772.html)
3. [http://liuqh.icu/2020/09/09/springboot-4-mybatisgenerator/](http://liuqh.icu/2020/09/09/springboot-4-mybatisgenerator/)