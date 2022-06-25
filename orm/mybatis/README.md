## 初识MyBatis
### MyBatis（https://github.com/mybatis/mybatis-3）
* **一款优秀的持久层框架**<br></br>
* **支持定制化SQL、存储过程和高级映射**<br></br>


### Spring整合MyBatis
* **MyBatis Spring Adapter (https://github.com/mybatis/spring)**<br></br>
* **MyBatis Spring-Boot-Starter (https://github.com/mybatis/spring-boot-starter)**<br></br>


### 简单配置
* **mybatis.config-location=classpath:mybatis-config.xml**<br></br>
* **mybatis.mapper-locations=classpath:/mapper/*.xml**<br></br>
* **mybatis.type-aliases-package=类型别名包名**<br></br>
* **mybatis.type-handlers-package=TypeHandler扫描包**<br></br>
* **mybatis.configuration.map-underscore-to-camel-case=true**<br></br>


### Mapper的定义与扫描
* **@MapperScan配置扫描位置**<br></br>
* **@Mapper定义接口**<br></br>
* **映射的定义--XML与注解**


```
@Mapper
public interface ICoffeeMapper {

    @Insert("insert into t_coffee (name, price, create_time, update_time)"
            + "values (#{name}, #{price}, now(), now())")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int save(Coffee coffee);
    
    @Select("select * from t_coffee where id = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "create_time", property = "createTime"),
            // map-underscore-to-camel-case = true 可以实现一样的效果
            // @Result(column = "update_time", property = "updateTime"),
    })
    Coffee findById(@Param("id") Long id);
}
```



***

## 参考资料
1. [https://segmentfault.com/a/1190000021057852](https://segmentfault.com/a/1190000021057852)
2. [http://www.mybatis.org/mybatis-3/configuration.html#settings](http://www.mybatis.org/mybatis-3/configuration.html#settings)
3. [https://github.com/mybatis/mybatis-3](https://github.com/mybatis/mybatis-3)
4. [https://github.com/mybatis/spring](https://github.com/mybatis/spring)
5. [https://github.com/mybatis/spring-boot-starter](https://github.com/mybatis/spring-boot-starter)
