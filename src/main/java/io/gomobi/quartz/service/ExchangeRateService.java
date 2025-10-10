package io.gomobi.quartz.service;

import io.gomobi.quartz.common.constant.CurrencyCode;
import io.gomobi.quartz.dto.data.GlobalExchangeRateDto;

import java.math.BigDecimal;
import java.util.List;

public interface ExchangeRateService {

    GlobalExchangeRateDto findExchangeRate(CurrencyCode from, List<CurrencyCode> to, BigDecimal amount, String userId);


}
