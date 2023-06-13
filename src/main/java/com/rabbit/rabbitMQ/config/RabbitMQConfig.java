package com.rabbit.rabbitMQ.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.queue.name}")
    private String queue;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.fanout.exchange.name}")
    private String fanout_exchange;
    @Value("${rabbitmq.routing.key.name}")
    private String routing_key;
    //Spring bean for rabbitMQ queue
    @Bean
    public Queue queueOne(){
        return new Queue("queueOne");
    }
    @Bean
    public Queue queueTwo(){
        return new Queue("queueTwo");
    }
    @Bean
    public Queue queueThree(){
        return new Queue("queueThree");
    }
    @Bean
    public Queue queueFour(){
        return new Queue("queueFour");
    }
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(exchange);
    }
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(fanout_exchange);
    }
    @Bean
    public Binding binding(){
        return BindingBuilder
                .bind(queueOne())
                .to(topicExchange())
                .with(routing_key);
    }
    @Bean
    public Binding bindingTwo(){
        return BindingBuilder
                .bind(queueTwo())
                .to(fanoutExchange());
    }

    @Bean
    public Binding bindingThree(){
        return BindingBuilder
                .bind(queueThree())
                .to(fanoutExchange());
    }
    @Bean
    public Binding bindingFour() {
        return BindingBuilder
                .bind(queueFour())
                .to(fanoutExchange());
    }
}
