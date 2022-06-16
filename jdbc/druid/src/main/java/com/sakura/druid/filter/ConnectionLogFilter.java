package com.sakura.druid.filter;

import com.alibaba.druid.filter.FilterChain;
import com.alibaba.druid.filter.FilterEventAdapter;
import com.alibaba.druid.proxy.jdbc.ConnectionProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @author DM
 * @version 1.0
 * @date 2022/06/16
 * @description Druid自定义Filter实现类
 */
@Slf4j
@Component
public class ConnectionLogFilter extends FilterEventAdapter {

    @Override
    public void connection_connectBefore(FilterChain chain, Properties info) {
        log.info("======================建立连接前======================");
        log.info("BEFORE CONNECTION!");
    }

    @Override
    public void connection_connectAfter(ConnectionProxy connection) {
        log.info("======================建立连接后======================");
        log.info("AFTER CONNECTION!");
    }

}
