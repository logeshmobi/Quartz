package io.gomobi.quartz.external;

import io.gomobi.quartz.domain.entity.ExchangeRate;

import java.util.List;

public interface ExchangeRateProvider {

    List<ExchangeRate> fetchAllSupportingCurrencyPair(final String providerName, final String url, final String clientId, String clientSecret);

}
