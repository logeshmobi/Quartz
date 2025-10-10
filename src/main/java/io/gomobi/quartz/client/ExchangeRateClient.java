package io.gomobi.quartz.client;

import java.io.IOException;

public interface ExchangeRateClient {

    void fetchAndPublish(String url, String clientId, String clientSecret) throws IOException,
                                                                                                InterruptedException;

}
