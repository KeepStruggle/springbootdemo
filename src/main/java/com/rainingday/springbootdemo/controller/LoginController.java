package com.rainingday.springbootdemo.controller;

import com.rainingday.springbootdemo.service.LoginService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * @version 1.8
 * @ClassName LoginController
 * @Description TODO
 * @Author James
 * @date 2020/6/13 16:16
 */
@RestController
@RequestMapping("tmall")
public class LoginController {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private LoginService loginService;

    /**
     * 异步处理2：使用springBoot自带async注解
     */
    @RequestMapping(value = "test1", method = RequestMethod.GET)
    public String test1() throws ExecutionException, InterruptedException {
        System.out.println(("============>" + Thread.currentThread().getName()));
        System.out.println("开始执行多线程任务=====》");
        String result = loginService.getTest1().get();

        System.out.println(result);
        System.out.println(("主线程任务继续执行============>" + Thread.currentThread().getName()));
        return "异步,正在解析......";
    }
}
