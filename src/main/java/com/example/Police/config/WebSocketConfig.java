package com.example.Police.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // This is the URL where the frontend (dashboard) will connect
        // We enable SockJS so it works even if the browser doesn't support WebSockets
        registry.addEndpoint("/ws-tracking").setAllowedOriginPatterns("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Prefix for messages sent FROM the server TO the client (e.g., /topic/patrol-1)
        registry.enableSimpleBroker("/topic");

        // Prefix for messages sent FROM the client TO the server (e.g., /app/ping)
        registry.setApplicationDestinationPrefixes("/app");
    }
}