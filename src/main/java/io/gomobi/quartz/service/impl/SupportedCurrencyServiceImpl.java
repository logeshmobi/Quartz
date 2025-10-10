package io.gomobi.quartz.service.impl;

import io.gomobi.quartz.common.constant.CurrencyCode;
import io.gomobi.quartz.domain.entity.SupportedCurrencies;
import io.gomobi.quartz.domain.repository.SupportedCurrenciesRepository;
import io.gomobi.quartz.service.SupportedCurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupportedCurrencyServiceImpl implements SupportedCurrencyService {

    private final SupportedCurrenciesRepository repository;

    @Override
    @Cacheable(
            key = "'all'",
            value = "currencyCode",
            unless = "#result == null || #result.isEmpty()"
    )
    public List<CurrencyCode> findAll() {
        return repository.findAll();
    }

    @Override
    @CacheEvict(
            key = "'all'",
            value = "currencyCode",
            allEntries = true
    )
    public boolean add(CurrencyCode currencyCode) {
        return false;
    }
}
