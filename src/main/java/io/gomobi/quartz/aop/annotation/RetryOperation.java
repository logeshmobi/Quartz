package io.gomobi.quartz.aop.annotation;

import java.lang.annotation.*;
import java.time.temporal.ChronoUnit;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RetryOperation {

    int maxRetry() default 2;
    ChronoUnit timeUnit() default ChronoUnit.SECONDS;
    int delayUnit() default 1;
}
