package com.rainingday.springbootdemo.exception;

import java.text.MessageFormat;

/**
 * @program: richvc
 * @Description:
 * @author: Mr.Cheng
 * @date: 2020/6/13 3:29 上午
 */
public class CustomException extends RuntimeException {

    JwtResultCode resultCode;

    public CustomException(JwtResultCode resultCode){
        super(resultCode.message());
        this.resultCode = resultCode;
    }

    public CustomException(JwtResultCode resultCode, Object... args){
        super(resultCode.message());
        String message = MessageFormat.format(resultCode.message(), args);
        resultCode.setMessage(message);
        this.resultCode = resultCode;
    }

    public JwtResultCode getResultCode(){
        return resultCode;
    }
}

