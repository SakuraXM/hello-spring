## REQUIRES_NEW VS NESTED
### REQUIRES_NEW
* **始终启动一个新事务**
* **两个事务没有关联**

### NESTED
* **在原事务内启动一个内嵌事务**
* **两个事务有关联**
* **外部事务回滚，内嵌事务也会回滚**

***

## 测试案例
**insertThenRollback()在REQUIRES_NEW状态下，
invokeInsertThenRollback()调用并抛出异常时，insertThenRollback()不会回滚；
insertThenRollback()在NESTED状态下，
invokeInsertThenRollback()调用并抛出异常时，insertThenRollback()回滚**

[//]: # (    @Transactional&#40;rollbackFor = {RollbackException.class}, propagation = Propagation.REQUIRES_NEW&#41;)
    @Override
    @Transactional(rollbackFor = {RollbackException.class}, propagation = Propagation.NESTED)
    public void insertThenRollback() throws RollbackException {
        fooDao.insertData("BBB");
        throw new RollbackException();
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