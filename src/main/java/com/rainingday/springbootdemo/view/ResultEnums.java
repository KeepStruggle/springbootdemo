package com.rainingday.springbootdemo.view;

/**
 * @version 1.8
 * @ClassName ResultEnums
 * @Description TODO
 * @Author James
 * @date 2020/6/11 10:50
 */
public enum ResultEnums {
    SUCCESS(0, "success"),

    FAILURE(1, "failure"),

    PARAM_ERROR(2, "incorrect parameter"),

    NONE(3, "none"),

    LOGIN_FAIL(4, "login failed, username or password is incorrect"),

    CUSTOMER_NOT_EXIST(20, "Customer does not exist"),

    CUSTOMER_NOT_FOUND_BY_NAME(21, "Customer can not find by name");

    private Integer code;

    private String message;

    ResultEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

