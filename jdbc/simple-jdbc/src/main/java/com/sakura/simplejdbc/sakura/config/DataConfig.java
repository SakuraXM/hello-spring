package com.sakura.simplejdbc.sakura.config;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

/**
 * @author DM
 * @version 1.0
 * @description H2 web页面控制台配置信息(http://localhost:8082 )
 * @date 2022/06/16
 */
@Configuration
public class DataConfig {

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2WebServer() throws SQLException {
        return Server.createWebServer("-web", "-webAllowOthers", "-webDaemon", "-webPort", "8082");
    }

}
