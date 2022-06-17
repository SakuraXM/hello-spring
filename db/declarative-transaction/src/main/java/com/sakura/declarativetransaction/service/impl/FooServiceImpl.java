package com.sakura.declarativetransaction.service.impl;

import com.sakura.declarativetransaction.dao.FooDao;
import com.sakura.declarativetransaction.exception.RollbackException;
import com.sakura.declarativetransaction.service.IFooService;
import org.springframework.aop.framework.AopContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author DM
 * @version 1.0
 * @description Foo服务接口实现
 * @date 2022/06/17
 */
@Service
public class FooServiceImpl implements IFooService {
    @Resource
    private FooDao fooDao;
    @Lazy
    @Resource
    private FooServiceImpl fooService;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void insertRecord() {
        fooDao.insertData("AAA");
    }

    @Override
    @Transactional(rollbackFor = {RollbackException.class})
    public void insertThenRollback() throws RollbackException {
        fooDao.insertData("BBB");
        throw new RollbackException();
    }

    @Override
    public void invokeInsertThenRollback() throws RollbackException {
        insertThenRollback();
    }

    @Override
    public long getCount(String parameter) {
        return fooDao.getCount(parameter);
    }

    @Override
    public void invokeInsertThenRollbackBySelfService() throws RollbackException {
        fooService.insertThenRollback();
    }

    @Override
    public void invokeInsertThenRollbackByAopContext() throws RollbackException {
        ((IFooService) AopContext.currentProxy()).insertThenRollback();
    }

    @Override
    @Transactional(rollbackFor = {RollbackException.class})
    public void invokeInsertThenRollbackAddTransaction() throws RollbackException {
        insertThenRollback();
    }

}
