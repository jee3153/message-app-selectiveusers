package com.example.message.configuration;

import com.example.message.handler.ChatWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker // enable Websocket server
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    /**
    * withSocketJS() provides fallback options for browsers that don't support websockets.*/
    @Override
    public void registerStompEndpoints(final StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS();
    }

    /**
    * This method is used to route messages from one client to another.
     * - setApplicationDestinationPrefixes("/app") defines that the messages
     * whose destination starts with "/app" should be routed to message handling methods.
     * - enableSimpleBroker("/topic") defines that the messages whose destination
     * starts with "/topic" should be routed to the message broker.
     * Message broker broadcasts messages to all the connected clients
     * who are subscribed to a particular topic.
    * */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");
    }
}
