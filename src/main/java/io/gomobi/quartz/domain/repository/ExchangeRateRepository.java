package io.gomobi.quartz.domain.repository;

import io.gomobi.quartz.common.constant.CurrencyCode;
import io.gomobi.quartz.domain.entity.ExchangeRate;

import java.util.List;

public interface ExchangeRateRepository {

    List<ExchangeRate> findLatestRatesByBaseAndTargetCurrencies(CurrencyCode baseCurrency, List<CurrencyCode> targetCurrencies);

    boolean saveAll(List<ExchangeRate> exchangeRates);
}
