package com.sakura.transactionpropagation.service;


import com.sakura.transactionpropagation.exception.RollbackException;

/**
 * @author DM
 * @version 1.0
 * @description Foo服务接口
 * @date 2022/06/18
 */
public interface IFooService {

    /**
     * @throws RollbackException
     * @description 插入数据后回滚
     */
    void insertThenRollback() throws RollbackException;

    /**
     * @throws RollbackException
     * @description 代理插入数据后回滚
     */
    void invokeInsertThenRollback() throws RollbackException;

    /**
     * @return long
     * @description 获取数据条数
     */
    long getCount(String parameter);

}
