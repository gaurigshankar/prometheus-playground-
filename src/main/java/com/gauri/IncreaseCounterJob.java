package com.gauri;


import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Configuration
@EnableScheduling
public class IncreaseCounterJob {
    private final Counter ordersCreatedCounter;

    public IncreaseCounterJob(MeterRegistry meterRegistry) {
        ordersCreatedCounter = meterRegistry.counter("orders.created");
    }

    @Scheduled(fixedDelay = 5000)
    public void increaseCounter() {
        ordersCreatedCounter.increment();
    }
}
