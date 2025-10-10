package io.gomobi.quartz.service;

import io.gomobi.quartz.domain.entity.ExchangeRateProviderConfiguration;

public interface ProviderService {

    ExchangeRateProviderConfiguration findByStatus(ExchangeRateProviderConfiguration.Status status);
}
