package com.sakura.mybatis.mapper;

import com.sakura.mybatis.model.Coffee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author DM
 * @version 1.0
 * @description 咖啡信息数据库操作接口
 * @date 2022/06/22
 */
@Mapper
public interface CoffeeMapper {
    /**
     * @param coffee 咖啡信息
     * @return int
     * @description 保存咖啡信息
     */
    int save(Coffee coffee);

    /**
     * @param id 主键
     * @return Coffee
     * @description 根据ID查询咖啡信息
     */
    Coffee findById(@Param("id") Long id);
}
