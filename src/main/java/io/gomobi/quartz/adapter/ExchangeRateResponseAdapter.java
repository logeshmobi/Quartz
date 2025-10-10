package io.gomobi.quartz.adapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.gomobi.quartz.domain.entity.ExchangeRate;

import java.util.List;

public interface ExchangeRateResponseAdapter {

    List<ExchangeRate> adapt(String rawResponse) throws JsonProcessingException;

}
