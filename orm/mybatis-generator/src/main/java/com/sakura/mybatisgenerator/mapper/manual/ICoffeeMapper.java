package com.sakura.mybatisgenerator.mapper.manual;

import com.sakura.mybatisgenerator.model.auto.Coffee;
import com.sakura.mybatisgenerator.model.auto.CoffeeExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author DM
 * @version 1.0
 * @description 咖啡信息数据库交互接口
 * @date 2022/06/26
 */
@Mapper
public interface ICoffeeMapper {

    /**
     * @param row 咖啡信息
     * @return int
     * @description 咖啡信息保存接口
     */
    int insert(Coffee row);

    /**
     * @param id 主键信息
     * @return Coffee
     * @description 根据ID获取咖啡信息
     */
    Coffee selectById(Long id);

    /**
     * @param example 条件
     * @return List<Coffee>
     * @description 根据条件查询咖啡信息
     */
    List<Coffee> selectByExample(CoffeeExample example);

}
