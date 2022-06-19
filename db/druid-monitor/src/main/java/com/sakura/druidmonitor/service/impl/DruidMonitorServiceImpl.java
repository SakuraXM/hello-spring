package com.sakura.druidmonitor.service.impl;

import com.sakura.druidmonitor.dao.FooDao;
import com.sakura.druidmonitor.service.IDruidMonitorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author DM
 * @version 1.0
 * @description Druid监控接口实现类
 * @date 2022/06/18
 */
@Slf4j
@Service
public class DruidMonitorServiceImpl implements IDruidMonitorService {
    @Resource
    private FooDao fooDao;

    @Override
    @Transactional
    public void selectForUpdate() {
        fooDao.selectForUpdate();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            log.error("selectForUpdate interruptedException");
        }
    }
}
