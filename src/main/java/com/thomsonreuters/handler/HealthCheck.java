package com.thomsonreuters.handler;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import netflix.karyon.health.HealthCheckHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

@Singleton
public class HealthCheck implements HealthCheckHandler {
    private static final Logger log = LoggerFactory.getLogger(HealthCheck.class);

    @Inject
    public HealthCheck() {
    }

    @PostConstruct
    public void init() {
        log.info("Health check initialized.");
    }

    @Override
    public int getStatus() {
        log.info("Health check called.");
        return 200;
    }
}
