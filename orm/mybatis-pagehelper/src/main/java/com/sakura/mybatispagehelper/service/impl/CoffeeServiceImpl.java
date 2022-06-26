package com.sakura.mybatispagehelper.service.impl;

import com.sakura.mybatispagehelper.mapper.CoffeeMapper;
import com.sakura.mybatispagehelper.model.Coffee;
import com.sakura.mybatispagehelper.service.ICoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author DM
 * @version 1.0
 * @description 咖啡服务接口实现
 * @date 2022/06/26
 */
@Slf4j
@Service
public class CoffeeServiceImpl implements ICoffeeService {
    @Resource
    private CoffeeMapper coffeeMapper;

    @Override
    public List<Coffee> findAllWithRowBounds(RowBounds rowBounds) {

        return coffeeMapper.findAllWithRowBounds(rowBounds);
    }

    @Override
    public List<Coffee> findAllWithParam(int pageNum, int pageSize) {
        return coffeeMapper.findAllWithParam(pageNum, pageSize);
    }
}
