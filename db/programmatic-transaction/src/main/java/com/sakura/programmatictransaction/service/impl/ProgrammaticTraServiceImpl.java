package com.sakura.programmatictransaction.service.impl;

import com.sakura.programmatictransaction.dao.FooDao;
import com.sakura.programmatictransaction.service.IProgrammaticTraService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;

/**
 * @author DM
 * @version 1.0
 * @description 编程式事务实现类
 * @date 2022/06/17
 */
@Slf4j
@Service
public class ProgrammaticTraServiceImpl implements IProgrammaticTraService {
    @Resource
    private FooDao fooDao;
    @Resource
    private TransactionTemplate transactionTemplate;

    @Override
    public void TransactionTest() {
        log.info("============================================");
        log.info("COUNT BEFORE TRANSACTION: {}", getCount());
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                fooDao.insertData();
                log.info("COUNT IN TRANSACTION: {}", getCount());
                status.setRollbackOnly();
            }
        });
        log.info("COUNT AFTER TRANSACTION: {}", getCount());
        log.info("============================================");
    }

    /**
     * @return long
     * @description 获取Foo表数据条数
     */
    private long getCount() {
        return fooDao.getCount();
    }

}
