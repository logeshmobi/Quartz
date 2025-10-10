package io.gomobi.quartz.event.listener;

import io.gomobi.quartz.event.bus.RequestAuditEvent;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExchangeRateAuditListener {

    public void auditRequestEvent(RequestAuditEvent requestAuditEvent){

    }

}
