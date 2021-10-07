package com.example.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Properties;

@Slf4j
@Configuration
public class RabbitConfig {


    private ServiceConfiguration serviceConfiguration;

    @Autowired
    public RabbitConfig(ServiceConfiguration serviceConfiguration) {
        this.serviceConfiguration = serviceConfiguration;
        log.info(serviceConfiguration.toString());
    }

    @Bean
    public Queue serviceQueue()
    {
        log.info(serviceConfiguration.getServiceNameQueue());
        return new Queue(serviceConfiguration.getServiceNameQueue(), false);
    }

    @Bean
    public DirectExchange exchange()
    {
        return new DirectExchange(serviceConfiguration.getExchangeNameExchange());
    }


    @Bean
    public Binding userBinding(Queue serviceQueue, DirectExchange exchange) {
        return BindingBuilder
                .bind(serviceQueue)
                .to(exchange)
                .with(serviceConfiguration.getNameRoutingKey()); // routing key
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }



}
