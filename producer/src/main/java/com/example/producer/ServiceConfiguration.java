package com.example.producer;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Getter
@Setter(AccessLevel.MODULE)
@ToString
@Configuration
@ConfigurationProperties(prefix = "service")
public class ServiceConfiguration {

    private String serviceNameQueue = "service.queue";
    private String exchangeNameExchange = "service.exchange";
    private String nameRoutingKey = "key";

}
