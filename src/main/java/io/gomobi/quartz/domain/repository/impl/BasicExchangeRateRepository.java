package io.gomobi.quartz.domain.repository.impl;

import io.gomobi.quartz.common.constant.CurrencyCode;
import io.gomobi.quartz.domain.entity.ExchangeRate;
import io.gomobi.quartz.domain.repository.ExchangeRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BasicExchangeRateRepository implements ExchangeRateRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<ExchangeRate> findLatestRatesByBaseAndTargetCurrencies(CurrencyCode baseCurrency, List<CurrencyCode> targetCurrencies) {


        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("baseCurrency", baseCurrency.name())
                .addValue("targetCurrencies", targetCurrencies.stream().map(CurrencyCode::name).toList())
                .addValue("limit", targetCurrencies.size());

        return jdbcTemplate.query(
                FIND_LATEST_RATES_FOR_BASE_AND_TARGETS,
                mapSqlParameterSource,
                new BeanPropertyRowMapper<>(ExchangeRate.class)
        );
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public boolean saveAll(List<ExchangeRate> exchangeRates) {
        SqlParameterSource[] sources = exchangeRates.stream()
                .map(exchangeRate -> new MapSqlParameterSource()
                        .addValue("baseCurrency", exchangeRate.getBaseCurrency())
                        .addValue("targetCurrency", exchangeRate.getTargetCurrency())
                        .addValue("liveRate", exchangeRate.getLiveRate())
                        .addValue("liveRateTimestamp", exchangeRate.getLiveRateTimestamp())
                        .addValue("createdAt", exchangeRate.getCreatedAt())
                        .addValue("updatedAt", exchangeRate.getUpdatedAt())
                )
                .toArray(SqlParameterSource[]::new);

        int[] insertedRowCount = jdbcTemplate.batchUpdate(INSERT_QUERY, sources);
        return insertedRowCount.length == exchangeRates.size();
    }




    String FIND_LATEST_RATES_FOR_BASE_AND_TARGETS =
            """
            SELECT
                BASE_CURRENCY, TARGET_CURRENCY, LIVE_RATE, LIVE_RATE_TIMESTAMP
            FROM
                EXCHANGE_RATE
            WHERE
                BASE_CURRENCY = :baseCurrency AND
                TARGET_CURRENCY IN (:targetCurrencies)
            ORDER BY LIVE_RATE_TIMESTAMP DESC
            LIMIT :limit
            """;

    String FIND_LATEST_RATE_LIST_FOR_BASE_AND_TARGETS =
            """
            SELECT
                BASE_CURRENCY, TARGET_CURRENCY, LIVE_RATE, LIVE_RATE_TIMESTAMP
            FROM
                EXCHANGE_RATE
            WHERE
                BASE_CURRENCY = :baseCurrency AND
                TARGET_CURRENCY IN (:targetCurrencies) AND
                LIVE_RATE_TIMESTAMP BETWEEN :startDateTime AND :endDateTime
            ORDER BY LIVE_RATE_TIMESTAMP DESC
            LIMIT :limit OFFSET :offset
            """;

    String INSERT_QUERY =
            """
            INSERT INTO EXCHANGE_RATE
                (BASE_CURRENCY, TARGET_CURRENCY, LIVE_RATE, LIVE_RATE_TIMESTAMP, CREATED_AT, UPDATED_AT)
            VALUES
                (:baseCurrency, :targetCurrency, :liveRate, :liveRateTimestamp, :createdAt, :updatedAt)
            """;

}
