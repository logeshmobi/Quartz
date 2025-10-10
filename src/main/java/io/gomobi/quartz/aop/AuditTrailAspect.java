package io.gomobi.quartz.aop;

import io.gomobi.quartz.aop.annotation.AuditTrail;
import io.gomobi.quartz.common.constant.CurrencyCode;
import io.gomobi.quartz.common.constant.Delimiter;
import io.gomobi.quartz.common.constant.RequestSource;
import io.gomobi.quartz.event.bus.RequestAuditEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static io.gomobi.quartz.common.constant.Delimiter.COMMA;

@Component
@Aspect
@Slf4j
@RequiredArgsConstructor
public class AuditTrailAspect {

    private final ApplicationEventPublisher publisher;

    @Before("@annotation(auditTrail)")
    public void audit(JoinPoint joinPoint, AuditTrail auditTrail) {
        Object[] args = joinPoint.getArgs();
        log.info("Action {} Auditing the request {}", joinPoint.getSourceLocation(), args);

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String[] parameterNames = signature.getParameterNames();

        RequestAuditEvent event = new RequestAuditEvent();

        for (int i = 0; i < args.length; i++) {
            Object requestData = args[i];
            String paramName = parameterNames[i];
            switch (requestData) {
                case BigDecimal amount ->
                    event.setAmount(amount);
                case CurrencyCode currencyCode
                        when paramName.equals("from") ->
                    event.setBaseCurrency(currencyCode.name());
                case CurrencyCode currencyCode
                        when paramName.equals("to") ->
                    event.setToCurrency(currencyCode.name());
                case List<?> toCurrencies when !toCurrencies.isEmpty() && toCurrencies.getFirst() instanceof CurrencyCode ->
                    event.setToCurrency(toCurrencies.stream().map(object -> (CurrencyCode)object)
                            .map(CurrencyCode::name)
                            .collect(Collectors.joining(COMMA)));
                case RequestSource requestSource ->
                    event.setRequestSource(requestSource);
                case String userId
                        when paramName.equals("id") ->
                    event.setMasterMid(userId);
                default -> log.debug("Skipping argument of type {} for param {}",
                        requestData.getClass().getSimpleName(), paramName);
            }
        }

        log.info("Publishing Audit event with data {}", event);
        publisher.publishEvent(event);
    }

}
