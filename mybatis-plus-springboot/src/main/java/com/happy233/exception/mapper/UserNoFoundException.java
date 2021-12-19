package com.happy233.exception.mapper;

/**
 *
 *
 * @author zy136
 * @date 2021/12/19 15:30
 */
public class UserNoFoundException extends RuntimeException {
    public UserNoFoundException() {
    }

    public UserNoFoundException(String message) {
        super(message);
    }
}
