package io.gomobi.quartz.common.mapper;

import io.gomobi.quartz.domain.entity.ExchangeRate;
import io.gomobi.quartz.dto.data.ToCurrencyDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GlobalMapper {

    private GlobalMapper(){}

    public static List<ToCurrencyDto> toCurrencyDtoList(List<ExchangeRate> exchangeRates, BigDecimal amount){
        List<ToCurrencyDto> result  = new ArrayList<>();

        for (ExchangeRate exchangeRate : exchangeRates){
            result.add(toCurrencyDto(exchangeRate, amount));
        }
        return result;
    }

    public static ToCurrencyDto toCurrencyDto(ExchangeRate exchangeRate, BigDecimal amount){
        return ToCurrencyDto.build(exchangeRate.getTargetCurrency(), exchangeRate.getLiveRate(), amount, exchangeRate.getLiveRateTimestamp());
    }
}
