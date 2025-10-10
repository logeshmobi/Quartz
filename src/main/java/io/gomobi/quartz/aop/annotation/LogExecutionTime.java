package io.gomobi.quartz.aop.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogExecutionTime {
    String value() default "DEFAULT";
}
