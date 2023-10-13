package network.service.kafka;

import lombok.extern.slf4j.Slf4j;
import network.service.SocketHandler;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.support.Acknowledgment;

@Slf4j
public class CustomMessageListener implements MessageListener<String, String> {
    private final SocketHandler socketHandler;

    public CustomMessageListener(SocketHandler socketHandler) {
        this.socketHandler = socketHandler;
    }

    @Override
    public void onMessage(ConsumerRecord<String, String> data) {
        log.info("we are here 1");
        socketHandler.sendMessageToUser(data.topic(), data.value());
    }

    @Override
    public void onMessage(ConsumerRecord<String, String> data, Acknowledgment acknowledgment) {
        MessageListener.super.onMessage(data, acknowledgment);
    }

    @Override
    public void onMessage(ConsumerRecord<String, String> data, Consumer<?, ?> consumer) {
        MessageListener.super.onMessage(data, consumer);
    }

    @Override
    public void onMessage(ConsumerRecord<String, String> data, Acknowledgment acknowledgment, Consumer<?, ?> consumer) {
        MessageListener.super.onMessage(data, acknowledgment, consumer);
    }
}

