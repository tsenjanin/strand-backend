package me.strand.service.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;

import static me.strand.utils.constants.SystemVariables.*;

@Service
public class KafkaConsumerService {

    private final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);
    private final KafkaConsumer<String, String> consumer;

    public KafkaConsumerService(ConsumerFactory<String, String> consumerFactory) {
        this.consumer = (KafkaConsumer<String, String>) consumerFactory.createConsumer();
        this.consumer.subscribe(Collections.singletonList(KAFKA_TOPIC));
    }


    @Scheduled(fixedDelay = 10000L)
    public void readMessages() {
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));

        if (records.isEmpty()) {
            logger.debug("No messages found");
            return;
        }

        for (ConsumerRecord<String, String> record : records) {
            // TODO: process comments/posts

            consumer.commitSync(Map.of(
                    new TopicPartition(record.topic(), record.partition()),
                    new OffsetAndMetadata(record.offset() + 1)
            ));

            logger.debug("Message committed: {}", record.value());
        }
    }
}
