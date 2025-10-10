package io.gomobi.quartz.event.bus;

public record ExchangeRatesFetchedEvent(
        String response,
        String provider
) {
}
