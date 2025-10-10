package io.gomobi.quartz.service.impl;

import io.gomobi.quartz.common.constant.CurrencyCode;
import io.gomobi.quartz.common.mapper.GlobalMapper;
import io.gomobi.quartz.domain.repository.ExchangeRateRepository;
import io.gomobi.quartz.dto.data.GlobalExchangeRateDto;
import io.gomobi.quartz.dto.data.ToCurrencyDto;
import io.gomobi.quartz.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GlobalExchangeRateService implements ExchangeRateService {

    private final ExchangeRateRepository basicExchangeRateRepository;

    @Override
    public GlobalExchangeRateDto findExchangeRate(CurrencyCode from, List<CurrencyCode> to, BigDecimal amount, String userId) {

        List<ToCurrencyDto> toCurrencyDtoList = GlobalMapper.toCurrencyDtoList(
                basicExchangeRateRepository.findLatestRatesByBaseAndTargetCurrencies(from, to),
                amount
        );
        return new GlobalExchangeRateDto(from, amount, toCurrencyDtoList);
    }





}
