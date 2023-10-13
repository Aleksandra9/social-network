package network.service;

import lombok.extern.slf4j.Slf4j;
import network.configuration.KafkaProperties;
import network.service.kafka.CustomMessageListener;
import network.service.kafka.KafkaConsumerUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class SocketHandler extends TextWebSocketHandler {

    private final Map<String, WebSocketSession> sessions;
    private final KafkaProperties kafkaProperties;
    private final CustomMessageListener customMessageListener;

    public SocketHandler(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
        this.sessions = new ConcurrentHashMap<>();
        this.customMessageListener = new CustomMessageListener(this);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        try {
            sessions.remove(session.getPrincipal().getName());
            KafkaConsumerUtil.stopConsumer(session.getPrincipal().getName());
            super.afterConnectionClosed(session, status);
        } catch (Exception e) {
            log.error("Cannot close session", e);
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        log.debug("connect user: {}", Objects.requireNonNull(session.getPrincipal()).getName());
        KafkaConsumerUtil.startOrCreateConsumers(session.getPrincipal().getName(), customMessageListener, kafkaProperties.getProperties());
        sessions.put(session.getPrincipal().getName(), session);
    }

    public void sendMessageToUser(String userId, String post) {
        try {
            if (sessions.containsKey(userId))
                sessions.get(userId).sendMessage(new TextMessage(post));
        } catch (IOException e) {
            log.error("Cannot send message to user {}", userId, e);
        }
    }
}
