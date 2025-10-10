package io.gomobi.quartz.event.listener;

import io.gomobi.quartz.event.bus.ExchangeRatesFetchedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProviderRequestLimitUpdateListener {

    @EventListener
    public void onExchangeRatesFetched(ExchangeRatesFetchedEvent event) {

    }
}
