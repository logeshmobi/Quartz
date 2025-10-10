package io.gomobi.quartz.service;

import io.gomobi.quartz.common.constant.CurrencyCode;

import java.util.List;

public interface SupportedCurrencyService {

    List<CurrencyCode> findAll();

    boolean add(CurrencyCode currencyCode);

}
