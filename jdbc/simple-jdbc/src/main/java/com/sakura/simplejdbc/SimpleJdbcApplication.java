package com.sakura.simplejdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.annotation.Resource;
import javax.sql.DataSource;

@SpringBootApplication
public class SimpleJdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleJdbcApplication.class, args);
	}

	@Bean
	@Resource
	public SimpleJdbcInsert simpleJdbcInsert(JdbcTemplate jdbcTemplate) {
		return new SimpleJdbcInsert(jdbcTemplate).withTableName("FOO").usingGeneratedKeyColumns("ID");
	}

	@Bean
	@Resource
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}

}
