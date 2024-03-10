package spring_and_kafka.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import spring_and_kafka.entity.Message;
@Service
public class SenderService {
    private final KafkaTemplate<String, Message> kafkaTemplate;

    public SenderService(KafkaTemplate<String, Message> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String topic, Message message) {
        kafkaTemplate.send(topic, message);
    }
}
