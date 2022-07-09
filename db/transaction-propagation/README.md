## REQUIRES_NEW VS NESTED
### REQUIRES_NEW
* **始终启动一个新事务**<br></br>
* **两个事务没有关联**<br></br>

### NESTED
* **在原事务内启动一个内嵌事务**<br></br>
* **两个事务有关联**<br></br>
* **外部事务回滚，内嵌事务也会回滚**<br></br>

***

## 测试案例
1. **insertThenRollback()在REQUIRES_NEW状态下，
invokeInsertThenRollback()调用并抛出异常时，insertThenRollback()不会回滚**<br></br>
2. **insertThenRollback()在NESTED状态下，
invokeInsertThenRollback()调用并抛出异常时，insertThenRollback()回滚**

```
@Override
@Transactional(rollbackFor = {RollbackException.class}, propagation = Propagation.REQUIRES_NEW)
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
```