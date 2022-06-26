package com.sakura.mybatispagehelper.mapper;

import com.sakura.mybatispagehelper.model.Coffee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @author DM
 * @version 1.0
 * @description 咖啡信息数据库操作接口
 * @date 2022/06/26
 */
@Mapper
public interface CoffeeMapper {
    /**
     * @param rowBounds 参数
     * @return List<Coffee>
     * @description 根据RowBounds查询数据(mybatis自带)
     */
    List<Coffee> findAllWithRowBounds(RowBounds rowBounds);

    /**
     * @param pageNum  分页数
     * @param pageSize 分页数
     * @return List<Coffee>
     * @description 根据pageNum和pageSize查询数据
     */
    List<Coffee> findAllWithParam(int pageNum, int pageSize);
}
