package com.ms.email.configs;

import com.rabbitmq.client.AMQP;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQ {
    @Value("${broker.queue.email.name}")
    private String queue;

    @Bean
    public AMQP.Queue queue() {
        return new AMQP.Queue(queue, true);
    }
}
