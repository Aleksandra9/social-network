package network.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class KafkaProperties {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    public Map<String, Object> getProperties() {
        var properties = new HashMap<String, Object>();
        properties.put("bootstrap.servers", bootstrapServers);
        properties.put("group.id", "network");
        properties.put("enable.auto.commit", true);
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("session.timeout.ms", "30000");
        properties.put("max.poll.records", "1");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return properties;
    }
}