package com.rainingday.springbootdemo.annotation;

import java.lang.annotation.*;

/**
 * @version 1.8
 * @ClassName JwtIgnore
 * @Description TODO
 * @Author James
 * @date 2020/6/13 17:35
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JwtIgnore {
}
