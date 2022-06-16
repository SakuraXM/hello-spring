package com.sakura.simplejdbc.sakura.service;

/**
 * @author DM
 * @version 1.0
 * @description Foo服务层接口
 * @date 2022/06/16
 */
public interface IFooService {
    /**
     * @description 插入数据
     */
    void insertData();

    /**
     * @description 查询数据列表
     */
    void listData();

    /**
     * @description 批量插入数据
     */
    void batchInsert();

}
