package io.gomobi.quartz.event.bus;

import io.gomobi.quartz.common.constant.RequestSource;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class RequestAuditEvent {
    RequestSource requestSource;
    String masterMid;
    String baseCurrency;
    String toCurrency;
    BigDecimal amount;
}
