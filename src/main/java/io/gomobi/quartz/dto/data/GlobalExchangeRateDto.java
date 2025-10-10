package io.gomobi.quartz.dto.data;

import io.gomobi.quartz.common.constant.CurrencyCode;

import java.math.BigDecimal;
import java.util.List;


public record GlobalExchangeRateDto(
        CurrencyCode from,
        BigDecimal amount,
        List<ToCurrencyDto> to
) {


}
