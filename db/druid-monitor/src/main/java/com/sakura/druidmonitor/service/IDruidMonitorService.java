package com.sakura.druidmonitor.service;

/**
 * @author DM
 * @version 1.0
 * @description Druid监控接口
 * @date 2022/06/18
 */
public interface IDruidMonitorService {

    /**
     * @description 悲观查询接口
     */
    void selectForUpdate();

}
