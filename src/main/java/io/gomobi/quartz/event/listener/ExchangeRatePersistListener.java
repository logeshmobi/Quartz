package io.gomobi.quartz.event.listener;

import io.gomobi.quartz.event.bus.ExchangeRatesFetchedEvent;
import io.gomobi.quartz.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExchangeRatePersistListener {

    private final ExchangeRateService exchangeRateService;

    @EventListener
    public void onExchangeRatesFetched(ExchangeRatesFetchedEvent event) {

    }

}
