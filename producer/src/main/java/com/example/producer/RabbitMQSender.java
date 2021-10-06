package com.example.producer;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RabbitMQSender {

    private RabbitTemplate rabbitTemplate;
    private ServiceConfiguration serviceConfiguration;


    @Autowired
    public RabbitMQSender(RabbitTemplate rabbitTemplate, ServiceConfiguration serviceConfiguration) {
        this.rabbitTemplate = rabbitTemplate;
        this.serviceConfiguration = serviceConfiguration;
    }


    public void send(Message message) {
        try {
            rabbitTemplate.convertAndSend(serviceConfiguration.getExchangeNameExchange(),message);
            log.info("Message sent {}", message);
        } catch (AmqpException e) {
            log.error(e.getMessage());
        }
    }


}
