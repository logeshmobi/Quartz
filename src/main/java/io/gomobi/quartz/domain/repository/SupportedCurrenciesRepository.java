package io.gomobi.quartz.domain.repository;

import io.gomobi.quartz.common.constant.CurrencyCode;

import java.util.List;

public interface SupportedCurrenciesRepository {

    boolean isExist(CurrencyCode currencyCode);

    boolean save(CurrencyCode currencyCode);

    boolean saveAll(List<CurrencyCode> currencyCodes);

    List<CurrencyCode> findAll();

}
