package com.sakura.predatasource.sakura.service;

import java.sql.SQLException;

/**
 * @author DM
 * @version 1.0
 * @description 服务接口
 * @date 2022/06/16
 */
public interface IPreDataSourceService {
    /**
     * @throws SQLException
     * @description 连接信息展示
     */
    void showConnection() throws SQLException;

    /**
     * @description 数据信息显示
     */
    void showData();

}
