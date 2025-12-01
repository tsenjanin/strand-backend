package me.strand.service.kafka;

import me.strand.model.ModerationResult;
import me.strand.model.rest.request.ModerationRequest;
import me.strand.service.comment.CommentService;
import me.strand.service.llm.ModerationService;
import me.strand.service.post.PostService;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import static me.strand.utils.constants.SystemVariables.*;
import static me.strand.utils.objectmapper.ObjectMapperUtils.*;

@Service
public class KafkaConsumerService {

    private final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);
    private final KafkaConsumer<String, String> consumer;
    private final ModerationService moderationService;
    private final KafkaUtils kafkaUtils;

    public KafkaConsumerService(ConsumerFactory<String, String> consumerFactory,
                                ModerationService moderationService, PostService postService,
                                CommentService commentService,
                                KafkaUtils kafkaUtils) {
        this.consumer = (KafkaConsumer<String, String>) consumerFactory.createConsumer();

//                this.consumer = new KafkaConsumer<>(Map.of(
//                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",
//                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
//                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
//                ConsumerConfig.GROUP_ID_CONFIG, "strand-consumer",
//                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"
//        ));

        this.moderationService = moderationService;
        this.consumer.subscribe(Collections.singletonList(KAFKA_TOPIC));
        this.kafkaUtils = kafkaUtils;
    }

    @Scheduled(fixedDelay = 10000L)
    public void readMessages() {
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));

        if (records.isEmpty()) {
            logger.debug("No messages found");
            return;
        }

        for (ConsumerRecord<String, String> record : records) {
            try {
                var result = moderationService.moderateContent(record.value());
                var content = convertJsonToObject(record.value(), ModerationRequest.class);
                var isRejected = (result != null)
                        && (Objects.equals(result.getResult(), ModerationResult.REJECT.name()));

                kafkaUtils.insertContent(content, isRejected);

                consumer.commitSync(Map.of(
                        new TopicPartition(record.topic(), record.partition()),
                        new OffsetAndMetadata(record.offset() + 1)
                ));

                logger.debug("Message committed: {}", record.value());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
