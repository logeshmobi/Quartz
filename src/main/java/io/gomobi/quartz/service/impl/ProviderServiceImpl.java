package io.gomobi.quartz.service.impl;

import io.gomobi.quartz.domain.entity.ExchangeRateProviderConfiguration;
import io.gomobi.quartz.domain.repository.ExchangeRateProviderRepository;
import io.gomobi.quartz.domain.repository.ExchangeRateRepository;
import io.gomobi.quartz.service.ProviderService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProviderServiceImpl implements ProviderService {

    private final ExchangeRateProviderRepository exchangeRateProviderRepository;

    @Override
    @Cacheable(
            key = "#status",
            unless = "#result == null"
    )
    public ExchangeRateProviderConfiguration findByStatus(ExchangeRateProviderConfiguration.Status status) {
        return exchangeRateProviderRepository.findByStatus(status);
    }


}
