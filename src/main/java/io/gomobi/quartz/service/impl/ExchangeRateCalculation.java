package io.gomobi.quartz.service.impl;

import io.gomobi.quartz.domain.entity.ExchangeRate;
import io.gomobi.quartz.service.Calculation;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExchangeRateCalculation implements Calculation<List<ExchangeRate>> {

    @Override
    public List<ExchangeRate> calculate(List<ExchangeRate> data) {
        List<ExchangeRate> calculatedExchangeRates = new ArrayList<>(data);

        int arrayLength = data.size();

        for (int i = 0; i < arrayLength; i++) {
            ExchangeRate exchangeRate = data.get(i);

            BigDecimal midRate = exchangeRate.getLiveRate();
            BigDecimal inversionRate = BigDecimal.ONE.divide(midRate, 10, RoundingMode.HALF_UP);

            ExchangeRate inversionExchangeRate = getInvertedExchangeRate(inversionRate, exchangeRate);
            calculatedExchangeRates.add(inversionExchangeRate);

            int index = i + 1;
            while(index < arrayLength - 1){

                index++;
            }
        }

        
        return calculatedExchangeRates;
    }

    private static ExchangeRate getInvertedExchangeRate(BigDecimal inversionRate, ExchangeRate exchangeRate) {
        ExchangeRate inversionExchangeRate = new ExchangeRate();
        inversionExchangeRate.setLiveRate(inversionRate);
        inversionExchangeRate.setLiveRateTimestamp(exchangeRate.getLiveRateTimestamp());
        inversionExchangeRate.setBaseCurrency(exchangeRate.getTargetCurrency());
        inversionExchangeRate.setTargetCurrency(exchangeRate.getBaseCurrency());
        inversionExchangeRate.setCreatedAt(exchangeRate.getCreatedAt());
        inversionExchangeRate.setUpdatedAt(exchangeRate.getUpdatedAt());
        inversionExchangeRate.setProviderName(exchangeRate.getProviderName());
        return inversionExchangeRate;
    }
}
