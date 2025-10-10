package io.gomobi.quartz.config;

import io.gomobi.quartz.common.constant.CurrencyCode;
import io.gomobi.quartz.exception.InvalidRequestData;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CurrencyConverter implements Converter<String, CurrencyCode> {

    @Override
    public CurrencyCode convert(String source) {

        if (source == null || !CurrencyCode.contains(source))
            throw new InvalidRequestData(source, "Received invalid currency code of "+ source);

        return CurrencyCode.valueOf(source);
    }
}
