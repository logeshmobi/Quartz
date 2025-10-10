package io.gomobi.quartz.dto.data;

import io.gomobi.quartz.common.constant.CurrencyCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ExchangeRateDto(
        String providerName,
        CurrencyCode from,
        BigDecimal rate,
        CurrencyCode to,
        LocalDateTime timeStamp
) {
}
