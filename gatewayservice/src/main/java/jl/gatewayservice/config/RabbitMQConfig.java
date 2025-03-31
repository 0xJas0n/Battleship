package jl.gatewayservice.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String GAME_EVENTS_EXCHANGE = "game.events";
    public static final String GAME_CREATED_QUEUE = "game.created.queue";
    public static final String GAME_CREATED_ROUTING_KEY = "game.created";

    @Bean
    public TopicExchange gameEventsExchange() {
        return new TopicExchange(GAME_EVENTS_EXCHANGE);
    }

    @Bean
    public Queue gameCreatedQueue() {
        return new Queue(GAME_CREATED_QUEUE);
    }

    @Bean
    public Binding gameCreatedBinding(Queue gameCreatedQueue, TopicExchange gameEventsExchange) {
        return BindingBuilder.bind(gameCreatedQueue)
                .to(gameEventsExchange)
                .with(GAME_CREATED_ROUTING_KEY);
    }
}
