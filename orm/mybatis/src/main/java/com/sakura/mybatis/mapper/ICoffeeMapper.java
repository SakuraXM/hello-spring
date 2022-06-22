package com.sakura.mybatis.mapper;

import com.sakura.mybatis.model.Coffee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * @author DM
 * @version 1.0
 * @description 咖啡信息数据库操作接口
 * @date 2022/06/22
 */
@Mapper
public interface ICoffeeMapper {

    /**
     * @param coffee 咖啡信息
     * @return int
     * @description 保存咖啡信息
     */
    @Insert("insert into t_coffee (name, price, create_time, update_time)"
            + "values (#{name}, #{price}, now(), now())")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int save(Coffee coffee);

    /**
     * @param id 主键
     * @return Coffee
     * @description 根据ID查询咖啡信息
     */
    @Select("select * from t_coffee where id = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "create_time", property = "createTime"),
            // map-underscore-to-camel-case = true 可以实现一样的效果
            // @Result(column = "update_time", property = "updateTime"),
    })
    Coffee findById(@Param("id") Long id);
}
