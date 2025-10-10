package io.gomobi.quartz.domain.repository.impl;

import io.gomobi.quartz.domain.entity.ExchangeRateProviderConfiguration;
import io.gomobi.quartz.domain.repository.ExchangeRateProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BasicExchangeRateProviderRepository implements ExchangeRateProviderRepository {

    private static final Class<ExchangeRateProviderConfiguration> EXCHANGE_RATE_PROVIDER_CONFIG = ExchangeRateProviderConfiguration.class;

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public ExchangeRateProviderConfiguration findByStatus(ExchangeRateProviderConfiguration.Status status) {
        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("status", status.name());
        return jdbcTemplate.queryForObject(
                FIND_BY_STATUS,
                param,
                EXCHANGE_RATE_PROVIDER_CONFIG
        );
    }

    @Override
    public List<ExchangeRateProviderConfiguration> findAll() {
        return jdbcTemplate.query(
                FIND_ALL,
                new BeanPropertyRowMapper<>(EXCHANGE_RATE_PROVIDER_CONFIG)
        );
    }

    @Transactional(rollbackFor = {Throwable.class})
    @Override
    public boolean updateRemainingRequestCount(int count, String providerName) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("count", count)
                .addValue("providerName", providerName);

        return jdbcTemplate.update(
                UPDATE_REMAINING_REQUEST_COUNT,
                parameterSource
        ) > 0;
    }



    String FIND_BY_STATUS =
            """
            SELECT
                *
            FROM
                EXCHANGE_RATE_PROVIDER_CONFIGURATION
            WHERE
                STATUS = :status
            """;

    String FIND_ALL =
            """
            SELECT
                *
            FROM
                EXCHANGE_RATE_PROVIDER_CONFIGURATION
            """;

    String UPDATE_REMAINING_REQUEST_COUNT =
            """
            UPDATE
                EXCHANGE_RATE_PROVIDER_CONFIGURATION
            SET
                REMAINING_REQUEST_COUNT = REMAINING_REQUEST_COUNT - :count
            WHERE
                PROVIDER_NAME = :providerName
            """;
}
