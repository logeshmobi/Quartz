package io.gomobi.quartz.domain.repository;

import io.gomobi.quartz.domain.entity.ExchangeRateProviderConfiguration;

import java.util.List;

public interface ExchangeRateProviderRepository {

    ExchangeRateProviderConfiguration findByStatus(ExchangeRateProviderConfiguration.Status status);

    List<ExchangeRateProviderConfiguration> findAll();

    boolean updateRemainingRequestCount(int i, String providerName);
}
