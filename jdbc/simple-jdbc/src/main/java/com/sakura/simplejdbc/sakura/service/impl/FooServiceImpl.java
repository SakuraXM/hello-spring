package com.sakura.simplejdbc.sakura.service.impl;

import com.sakura.simplejdbc.sakura.dao.BatchFooDao;
import com.sakura.simplejdbc.sakura.dao.FooDao;
import com.sakura.simplejdbc.sakura.service.IFooService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author DM
 * @version 1.0
 * @description Foo服务实现
 * @date 2022/06/16
 */
@Slf4j
@Service
public class FooServiceImpl implements IFooService {
    @Resource
    private FooDao fooDao;
    @Resource
    private BatchFooDao batchFooDao;

    @Override
    public void insertData() {
        log.info("======================开始插入数据======================");
        fooDao.insertData();
        log.info("======================插入数据结束======================");
    }

    @Override
    public void listData() {
        log.info("======================开始查询数据======================");
        fooDao.listData();
        log.info("======================查询数据结束======================");
    }

    @Override
    public void batchInsert() {
        log.info("======================开始批量插入数据======================");
        batchFooDao.batchInsert();
        log.info("======================批量插入数据结束======================");
    }
}
