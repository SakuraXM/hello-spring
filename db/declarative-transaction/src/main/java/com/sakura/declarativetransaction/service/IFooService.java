package com.sakura.declarativetransaction.service;

import com.sakura.declarativetransaction.exception.RollbackException;

/**
 * @author DM
 * @version 1.0
 * @description Foo服务接口
 * @date 2022/06/17
 */
public interface IFooService {
    /**
     * @description 插入数据
     */
    void insertRecord();

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

    /**
     * @throws RollbackException
     * @description 通过本身代理插入数据后回滚
     */
    void invokeInsertThenRollbackBySelfService() throws RollbackException;

    /**
     * @throws RollbackException
     * @description 获取当前代理插入数据后回滚
     */
    void invokeInsertThenRollbackByAopContext() throws RollbackException;

    /**
     * @throws RollbackException
     * @description 加入事务后插入数据后回滚
     */
    void invokeInsertThenRollbackAddTransaction() throws RollbackException;

}
