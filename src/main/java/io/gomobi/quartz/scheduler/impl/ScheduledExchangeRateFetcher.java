//package io.gomobi.quartz.scheduler.impl;
//
//import io.gomobi.quartz.domain.entity.ExchangeRateProviderConfiguration;
//import io.gomobi.quartz.scheduler.ExchangeRateFetcher;
//import io.gomobi.quartz.service.ProviderService;
//import io.gomobi.quartz.client.ClientFactory;
//import jakarta.annotation.PostConstruct;
//import lombok.RequiredArgsConstructor;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
//import org.springframework.stereotype.Service;
//
//import java.time.Duration;
//import java.time.Instant;
//import java.time.temporal.ChronoUnit;
//import java.util.concurrent.ScheduledFuture;
//
//@Service
//@RequiredArgsConstructor
//public class ScheduledExchangeRateFetcher implements ExchangeRateFetcher {
//
//    private final ThreadPoolTaskScheduler taskScheduler;
//    private ScheduledFuture<?> scheduledFuture;
//    private final ProviderService providerService;
//    private final ClientFactory clientFactory;
//
//    @PostConstruct
//    public void init(){
//
//    }
//
//
//    @Override
//    public void fetchAndPersistLiveRates() {
//
//        if (scheduledFuture != null)
//            scheduledFuture.cancel(false);
//
//        ExchangeRateProviderConfiguration providerData = providerService.findByStatus(ExchangeRateProviderConfiguration.Status.ACTIVE);
//        ChronoUnit scheduleIntervalUnit = providerData.getScheduleUnit()
//                .toChronoUnit();
//        scheduledFuture = taskScheduler.scheduleAtFixedRate(
//                () -> clientFactory.process(providerData.getProviderName(), providerData.getProviderUrl(), providerData.getClientId(), providerData.getClientSecret()),
//                Instant.now().truncatedTo(scheduleIntervalUnit),
//                Duration.of(providerData.getScheduleInterval(),scheduleIntervalUnit)
//        );
//
//    }
//
//    @Override
//    public boolean refresh() {
//
//        return false;
//    }
//}