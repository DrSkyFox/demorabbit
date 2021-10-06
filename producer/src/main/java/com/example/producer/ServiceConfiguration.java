package com.example.producer;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Getter
@Setter(AccessLevel.MODULE)
@Configuration
@ConfigurationProperties(prefix = "service")
public class ServiceConfiguration {

    private String serviceNameQueue = "e-service.queue";
    private String exchangeNameExchange = "e-service.exchange";
    private String nameRoutingKey = "key";

}
