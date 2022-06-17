package com.sakura.errorcode.exception;

import org.springframework.dao.DuplicateKeyException;

/**
 * @author DM
 * @version 1.0
 * @description 自定义主键冲突异常
 * @date 2022/06/17
 */
public class CustomDuplicatedKeyException extends DuplicateKeyException {
    public CustomDuplicatedKeyException(String msg) {
        super(msg);
    }

    public CustomDuplicatedKeyException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
