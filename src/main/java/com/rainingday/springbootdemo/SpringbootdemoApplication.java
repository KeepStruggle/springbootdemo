package com.rainingday.springbootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
public class SpringbootdemoApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringbootdemoApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootdemoApplication.class, args);
    }

    @Bean("taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);   //核心线程数
        executor.setMaxPoolSize(20);    //最大线程数
        executor.setQueueCapacity(500); //队列最大长度
        executor.setKeepAliveSeconds(60);   //程池维护线程所允许的空闲时间
        executor.setWaitForTasksToCompleteOnShutdown(true); //线程池对拒绝任务(无线程可用)的处理策略
        executor.setThreadNamePrefix("springbootdemo-");    //线程名前缀
        //executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());//配置拒绝策略
        executor.initialize();
        return executor;
    }

    /*
    * 拒绝策略
        rejectedExectutionHandler参数字段用于配置绝策略，常用拒绝策略如下：
        AbortPolicy：用于被拒绝任务的处理程序，它将抛出RejectedExecutionException
        CallerRunsPolicy：用于被拒绝任务的处理程序，它直接在execute方法的调用线程中运行被拒绝的任务。
        DiscardOldestPolicy：用于被拒绝任务的处理程序，它放弃最旧的未处理请求，然后重试execute。
        DiscardPolicy：用于被拒绝任务的处理程序，默认情况下它将丢弃被拒绝的任务。
    * 处理流程
       1.当一个任务被提交到线程池时，首先查看线程池的核心线程是否都在执行任务，否就选择一条线程执行任务，就是执行第二步。
       2.查看核心线程池是否已满，不满就创建一条线程执行任务，否则执行第三步。
       3.查看任务队列是否已满，不满就将任务存储在任务队列中，否则执行第四步。
       4.查看线程池是否已满，不满就创建一条线程执行任务，否则就按照策略处理无法执行的任务。
    * */
}
