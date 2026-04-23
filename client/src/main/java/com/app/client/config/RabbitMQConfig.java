package com.app.client.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue clientObtainQueue() {
        return new Queue("client.obtain", true);
    }

    @Bean
    public TopicExchange clientExchange() {
        return new TopicExchange("client.exchange");
    }

    @Bean
    public Binding bindingClientValidate(Queue clientObtainQueue, TopicExchange clientExchange) {
        return BindingBuilder.bind(clientObtainQueue)
                .to(clientExchange)
                .with("client.validate");
    }
}
