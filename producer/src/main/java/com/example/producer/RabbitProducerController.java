package com.example.producer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping(value = "/send")
public class RabbitProducerController {


    private final RabbitMQSender sender;
    private final AtomicLong id = new AtomicLong(0);

    @Autowired
    public RabbitProducerController(RabbitMQSender sender) {
        this.sender = sender;
    }

    @GetMapping(value = "/start")
    public String send() {
        sender.send(Message.builder().message("Hello").id(id.incrementAndGet()).build());
        return "Message send success";
    }


}
