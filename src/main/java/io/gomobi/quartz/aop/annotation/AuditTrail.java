package io.gomobi.quartz.aop.annotation;

import io.gomobi.quartz.common.constant.RequestSource;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuditTrail {

    RequestSource requestSource() default RequestSource.UNKNOWN;

    String action() default "UNKNOWN";
}
