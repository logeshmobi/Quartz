package io.gomobi.quartz.adapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.gomobi.quartz.common.constant.CurrencyCode;
import io.gomobi.quartz.domain.entity.ExchangeRate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class XecdResponseAdapter implements ExchangeRateResponseAdapter {

    private final ObjectMapper objectMapper;

    private static final DateTimeFormatter UTC_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssz");

    @Override
    public List<ExchangeRate> adapt(String rawResponse) throws JsonProcessingException {
        List<ExchangeRate> exchangeRates = new LinkedList<>();
        JsonNode jsonNode = objectMapper.readTree(rawResponse);

        LocalDateTime now = LocalDateTime.now();
        OffsetDateTime timeStamp = OffsetDateTime.parse(jsonNode.get("timestamp")
                .asText());

        CurrencyCode baseCurrency = CurrencyCode.valueOf(jsonNode.get("from")
                .asText());

        for (JsonNode node : jsonNode.withArrayProperty("to")) {
            CurrencyCode targetCurrency = CurrencyCode.valueOf(node.get("quotecurrency")
                    .asText());

            BigDecimal midRate = new BigDecimal(node.get("mid")
                    .asText());

            ExchangeRate exchangeRate = new ExchangeRate();
            exchangeRate.setBaseCurrency(baseCurrency);
            exchangeRate.setTargetCurrency(targetCurrency);
            exchangeRate.setLiveRate(midRate);
            exchangeRate.setLiveRateTimestamp(timeStamp);
            exchangeRate.setProviderName("xecd");
            exchangeRate.setCreatedAt(now);
            exchangeRate.setUpdatedAt(now);

            exchangeRates.add(exchangeRate);
        }

        return exchangeRates;
    }

}
