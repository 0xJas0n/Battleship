package jl.gatewayservice.application.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class GatewayService {
    @RabbitListener(queues = "game.created.queue")
    public void handleGameCreated(Long gameId) {
        System.out.println("Received game created event for game ID: " + gameId);
    }
}
