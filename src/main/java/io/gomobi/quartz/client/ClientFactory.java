package io.gomobi.quartz.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class ClientFactory {

    private final Map<String, ExchangeRateClient> clientServiceMap;

    public ClientFactory(Map<String, ExchangeRateClient> clientServiceMap) {
        this.clientServiceMap = clientServiceMap;
    }

    public void process(String providerName, String baseUrl, String clientId, String clientSecret) {
        try {
            clientServiceMap.get(providerName)
                    .fetchAndPublish(
                            baseUrl,
                            clientId,
                            clientSecret
                    );
        } catch (Throwable throwable) {
            log.error(
                    "{} client call were failed due to {}", providerName.toUpperCase(), throwable.getMessage(),
                    throwable
            );
        }
    }
}
