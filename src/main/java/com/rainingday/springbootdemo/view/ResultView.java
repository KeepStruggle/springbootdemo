package com.rainingday.springbootdemo.view;

import lombok.Data;

/**
 * @version 1.8
 * @ClassName ResultView
 * @Description TODO
 * @Author James
 * @date 2020/6/11 10:40
 */
@Data
public class ResultView<T> {
    /** code */
    private Integer code;

    /** message */
    private String msg;

    /** content of data */
    private T data;

    public ResultView(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public ResultView(Integer code, String msg,T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultView(){
    }
}
