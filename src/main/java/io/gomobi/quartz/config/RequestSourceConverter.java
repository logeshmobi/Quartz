package io.gomobi.quartz.config;

import io.gomobi.quartz.common.constant.RequestSource;
import io.gomobi.quartz.exception.InvalidRequestData;
import org.springframework.core.convert.converter.Converter;

public class RequestSourceConverter implements Converter<String, RequestSource> {
    @Override
    public RequestSource convert(String source) {
        if (source == null || !RequestSource.contains(source))
            throw new InvalidRequestData(source, "Received Request Source is "+source);

        return RequestSource.valueOf(source);
    }
}
