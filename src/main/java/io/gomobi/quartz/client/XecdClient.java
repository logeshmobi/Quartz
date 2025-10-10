package io.gomobi.quartz.client;

import io.gomobi.quartz.common.constant.CurrencyCode;
import io.gomobi.quartz.event.bus.ExchangeRatesFetchedEvent;
import io.gomobi.quartz.service.SupportedCurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.gomobi.quartz.common.util.EncodingUtil.basicAuthEncoding;

@Service(value = "xecd")
@RequiredArgsConstructor
public class XecdClient implements ExchangeRateClient {

    private final HttpClient httpClient;
    private final ApplicationEventPublisher publisher;
    private final SupportedCurrencyService currencyService;

    private HttpRequest httpRequest;
    private String baseUrl;
    private String clientId;
    private String clientSecret;
    private final Set<CurrencyCode> supportedCurrencies = new HashSet<>();

    @Override
    public void fetchAndPublish(String baseUrl, String clientId, String clientSecret) throws IOException,
                                                                                                           InterruptedException {

        List<CurrencyCode> supportedCurrencies = currencyService.findAll();

        supportedCurrencies.remove(CurrencyCode.USD);

        String toCurrencies = String.join(",", supportedCurrencies.stream()
                .map(CurrencyCode::name)
                .toList());

        if (httpRequest == null ||
            !clientId.equals(this.clientId) ||
            !clientSecret.equals(this.clientSecret) ||
            !baseUrl.equals(this.baseUrl) ||
            this.supportedCurrencies.size() != supportedCurrencies.size()
        ) {
            this.baseUrl = baseUrl;
            this.clientId = clientId;
            this.clientSecret = clientSecret;
            this.supportedCurrencies.addAll(supportedCurrencies);
            String url = this.baseUrl + "?USD&%s".formatted(toCurrencies);

            this.httpRequest = HttpRequest.newBuilder(URI.create(url))
                    .GET()
                    .header("Authorization", "Basic " + basicAuthEncoding(clientId, clientSecret))
                    .build();
        }

        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
        publisher.publishEvent(new ExchangeRatesFetchedEvent(httpResponse.body(), "xced"));
    }
}
