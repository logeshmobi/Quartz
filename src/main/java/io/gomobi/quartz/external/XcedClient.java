package io.gomobi.quartz.external;

import io.gomobi.quartz.domain.entity.ExchangeRate;

import java.util.List;

public class XcedClient implements ExchangeRateProvider{

    @Override
    public List<ExchangeRate> fetchAllSupportingCurrencyPair(
            String providerName,
            String url,
            String clientId,
            String clientSecret
    ) {

        return List.of();
    }
}
