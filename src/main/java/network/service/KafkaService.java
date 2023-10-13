package network.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import network.dto.PostWSModel;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class KafkaService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public KafkaService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        objectMapper = new ObjectMapper();
    }

    public void sendMessage(String topic, PostWSModel post) {
        try {
            kafkaTemplate.send(topic, UUID.randomUUID().toString(), objectMapper.writeValueAsString(post));
        } catch (Exception e) {
            log.error("Cannot convert send kafka message", e);
        }
    }
}
