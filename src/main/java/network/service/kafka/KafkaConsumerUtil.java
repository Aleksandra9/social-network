package network.service.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public final class KafkaConsumerUtil {
    private static final Map<String, ConcurrentMessageListenerContainer<String, String>> consumersMap = new HashMap<>();

    public static void startOrCreateConsumers(
            final String topic,
            final CustomMessageListener listener,
            final Map<String, Object> consumerProperties) {
        log.debug("creating kafka consumer for topic {}", topic);

        ConcurrentMessageListenerContainer<String, String> container = consumersMap.get(topic);
        if (container != null) {
            if (!container.isRunning()) {
                log.debug("Consumer already created for topic {}, starting consumer!!", topic);
                container.start();
                log.debug("Consumer for topic {} started!!!!", topic);
            }
            return;
        }
        ContainerProperties containerProps = new ContainerProperties(topic);
        containerProps.setPollTimeout(100);

        ConsumerFactory<String, String> factory = new DefaultKafkaConsumerFactory<>(consumerProperties);
        container = new ConcurrentMessageListenerContainer<>(factory, containerProps);
        container.setupMessageListener(listener);
        container.start();
        consumersMap.put(topic, container);
        log.info("created and started kafka consumer for topic {}", topic);
    }

    public static void stopConsumer(final String topic) {
        log.info("stopping consumer for topic {}", topic);
        ConcurrentMessageListenerContainer<String, String> container = consumersMap.get(topic);
        container.stop();
        log.info("consumer stopped!!");
    }

}

