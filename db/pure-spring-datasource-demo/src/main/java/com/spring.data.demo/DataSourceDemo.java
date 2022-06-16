package com.spring.data.demo;

import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class DataSourceDemo {
    @Resource
    private DataSource dataSource;

    public static void main(String[] args) throws SQLException {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext*.xml");
        showBeans(applicationContext);
        dataSourceDemo(applicationContext);
    }

//    @Bean(destroyMethod = "close")
//    public DataSource dataSource() throws Exception {
//        Properties properties = new Properties();
//        properties.setProperty("driverClassName", "org.h2.Driver");
//        properties.setProperty("url", "jdbc:h2:mem:testdb");
//        properties.setProperty("username", "sa");
//        return BasicDataSourceFactory.createDataSource(properties);
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager() throws Exception {
//        return new DataSourceTransactionManager(dataSource());
//    }

    private static void showBeans(ApplicationContext applicationContext) {
        System.out.println("======================Spring管理Bean内容=============================");
        System.out.println(Arrays.toString(applicationContext.getBeanDefinitionNames()));
        System.out.println("======================Spring管理Bean内容=============================");
    }

    private static void dataSourceDemo(ApplicationContext applicationContext) throws SQLException {
        DataSourceDemo demo = applicationContext.getBean("dataSourceDemo", DataSourceDemo.class);
        demo.showDataSource();
    }

    public void showDataSource() throws SQLException {
        System.out.println("======================dataSource信息=============================");
        System.out.println(dataSource.toString());
        System.out.println("======================dataSource信息=============================");
        Connection conn = dataSource.getConnection();
        System.out.println("======================dataSource连接信息=============================");
        System.out.println(conn.toString());
        System.out.println("======================dataSource连接信息=============================");
        conn.close();
    }
}

