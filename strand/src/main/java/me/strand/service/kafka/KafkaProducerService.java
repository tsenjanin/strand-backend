package me.strand.service.kafka;

import lombok.RequiredArgsConstructor;
import me.strand.model.rest.request.ModerationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static me.strand.utils.mapper.ObjectMapperUtils.*;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void queueContent(String topic, ModerationRequest moderationRequest) {
        try {
            kafkaTemplate.send(topic, convertObjectToJson(moderationRequest));

            logger.info("Sent message '{}' to '{}'.", moderationRequest, topic);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
