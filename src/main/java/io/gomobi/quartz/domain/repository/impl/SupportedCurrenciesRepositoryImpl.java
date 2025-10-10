package io.gomobi.quartz.domain.repository.impl;

import io.gomobi.quartz.common.constant.CurrencyCode;
import io.gomobi.quartz.domain.entity.SupportedCurrencies;
import io.gomobi.quartz.domain.repository.SupportedCurrenciesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SupportedCurrenciesRepositoryImpl implements SupportedCurrenciesRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public boolean isExist(CurrencyCode currencyCode) {
        return jdbcTemplate.queryForObject(
                IS_EXIST,
                Collections.singletonMap("currency", currencyCode),
                new BeanPropertyRowMapper<>(SupportedCurrencies.class)
        ) != null;
    }

    @Override
    public boolean save(CurrencyCode currencyCode) {
        return false;
    }

    @Override
    public boolean saveAll(List<CurrencyCode> currencyCodes) {
        return false;
    }

    @Override
    public List<CurrencyCode> findAll() {
        return jdbcTemplate.query(
                FIND_ALL,
                new BeanPropertyRowMapper<>(CurrencyCode.class)
        );
    }


    String IS_EXIST =
            """
            SELECT
                CURRENCY
            FROM
                SUPPORTED_CURRENCIES
            WHERE
                CURRENCY = :currency
            """;

    String FIND_ALL =
            """
            SELECT
                CURRENCY
            FROM
                SUPPORTED_CURRENCIES
            """;
}
