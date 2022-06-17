package com.sakura.errorcode;

import com.sakura.errorcode.exception.CustomDuplicatedKeyException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ErrorCodeApplicationTests {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Test(expected = CustomDuplicatedKeyException.class)
    public void testThrowingCustomException() {
        jdbcTemplate.execute("INSERT INTO FOO (ID, BAR) VALUES (1, 'A')");
        jdbcTemplate.execute("INSERT INTO FOO (ID, BAR) VALUES (1, 'B')");
    }

}
