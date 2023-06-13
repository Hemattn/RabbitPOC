package com.rabbit.rabbitMQ.controller;

import com.rabbit.rabbitMQ.producer.RabbitMQProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/java")
public class MessageController {
    private RabbitMQProducer rabbitMQProducer;

    public MessageController(RabbitMQProducer rabbitMQProducer) {
        this.rabbitMQProducer = rabbitMQProducer;
    }
    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message){
        rabbitMQProducer.sendMessage(message);
        return ResponseEntity.ok("Message sent.!");
    }
    @GetMapping("/fanout")
    public ResponseEntity<String> sendFanoutMessage(@RequestParam("message") String message){
        rabbitMQProducer.sendFanoutMessage(message);
        return ResponseEntity.ok("Messages sent.!");
    }
}
