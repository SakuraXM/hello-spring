package com.sakura.transactionpropagation.service.impl;

import com.sakura.transactionpropagation.dao.FooDao;
import com.sakura.transactionpropagation.exception.RollbackException;
import com.sakura.transactionpropagation.service.IFooService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author DM
 * @version 1.0
 * @description Foo服务接口实现
 * @date 2022/06/18
 */
@Slf4j
@Service
public class FooServiceImpl implements IFooService {
    @Resource
    private FooDao fooDao;
    @Lazy
    @Resource
    private IFooService fooService;

    @Override
    @Transactional(rollbackFor = {RollbackException.class}, propagation = Propagation.REQUIRES_NEW)
//    @Transactional(rollbackFor = {RollbackException.class}, propagation = Propagation.NESTED)
    public void insertThenRollback() throws RollbackException {
        fooDao.insertData("BBB");
//        throw new RollbackException();
    }

    @Override
    @Transactional(rollbackFor = {RollbackException.class})
    public void invokeInsertThenRollback() throws RollbackException {
        fooDao.insertData("AAA");
        try {
            fooService.insertThenRollback();
        } catch (RollbackException e) {
            log.error("RollbackException", e);
        }
        throw new RuntimeException();
    }

    @Override
    public long getCount(String parameter) {
        return fooDao.getCount(parameter);
    }

}
