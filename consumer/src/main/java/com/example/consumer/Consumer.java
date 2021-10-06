package com.example.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(queues = "e-service.queue")
public class Consumer {

    @RabbitHandler
    public void process(Message message) {
        log.info("message is {}", message);
    }

}
