package io.gomobi.quartz.scheduler;

public interface ExchangeRateFetcher {

    void fetchAndPersistLiveRates();

    boolean refresh();

}
