package com.rainingday.springbootdemo.service.impl;

import com.rainingday.springbootdemo.service.LoginService;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * @version 1.8
 * @ClassName LoginServiceImpl
 * @Description TODO
 * @Author James
 * @date 2020/6/13 16:17
 */
@Service
public class LoginServiceImpl implements LoginService {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());
    /**异步方法
     * 有@Async注解的方法，默认就是异步执行的，会在默认的线程池中执行，但是此方法不能在本类调用；启动类需添加直接开启异步执行@EnableAsync。
     * */
    @Async("taskExecutor")
    @Override
    public CompletableFuture<String> getTest1(){

            try {
                Thread.sleep(10000);
                for (int i = 1;i <= 100;i++){
                    System.out.println(Thread.currentThread().getName()+"----------异步：>"+i);
                }
                return CompletableFuture.completedFuture("执行异步任务完毕");
            }catch (Exception ex){
                ex.printStackTrace();
            }
            return CompletableFuture.completedFuture(Thread.currentThread().getName()+"执行完毕");
    }
}
