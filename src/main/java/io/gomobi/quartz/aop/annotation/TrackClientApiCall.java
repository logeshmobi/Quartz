package io.gomobi.quartz.aop.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TrackClientApiCall {

    String client() default "xecd";
}
