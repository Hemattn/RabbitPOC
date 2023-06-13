package com.rabbit.rabbitMQ.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.fanout.exchange.name}")
    private String fanout_exchange;
    @Value("${rabbitmq.routing.key.name}")
    private String routing_key;
    private static final Logger LOGGER=LoggerFactory.getLogger(RabbitMQProducer.class);
    private RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message){
        LOGGER.info(String.format("Message sent -> %s", message));
        rabbitTemplate.convertAndSend(exchange,routing_key,message);
    }

    public void sendFanoutMessage(String message){
        LOGGER.info(String.format("Messages sent -> %s", message));
        rabbitTemplate.convertAndSend(fanout_exchange,"",message);
    }
}

