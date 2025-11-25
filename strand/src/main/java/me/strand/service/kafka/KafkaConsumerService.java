package me.strand.service.kafka;

import me.strand.model.rest.request.InsertCommentRequest;
import me.strand.model.rest.request.InsertPostRequest;
import me.strand.model.rest.request.ModerationRequest;
import me.strand.service.comment.CommentService;
import me.strand.service.llm.ModerationService;
import me.strand.service.post.PostService;
import me.strand.utils.mapper.ObjectMapperUtils;
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

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import static me.strand.utils.constants.SystemVariables.*;
import static me.strand.utils.mapper.ObjectMapperUtils.*;

@Service
public class KafkaConsumerService {

    private final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);
    private final KafkaConsumer<String, String> consumer;
    private final ModerationService moderationService;
    private final PostService postService;
    private final CommentService commentService;

    public KafkaConsumerService(ConsumerFactory<String, String> consumerFactory,
                                ModerationService moderationService, PostService postService,
                                CommentService commentService
    ) {
        this.consumer = (KafkaConsumer<String, String>) consumerFactory.createConsumer();
        this.moderationService = moderationService;
        this.postService = postService;
        this.commentService = commentService;
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
            try {
                var result = moderationService.moderateContent(record.value());
                var content = convertJsonToObject(record.value(), ModerationRequest.class);
                var isRejected = result != null && Objects.equals(result.getResult(), "PASS");

                switch (content.getType()) {
                    case "POST" -> {
                        InsertPostRequest postRequest =
                                convertJsonToObject(content.getData(), InsertPostRequest.class);

                        postService.insertPost(postRequest, isRejected);
                    }
                    case "COMMENT" -> {
                        InsertCommentRequest commentRequest =
                                convertJsonToObject(content.getData(), InsertCommentRequest.class);

                        commentService.insertComment(commentRequest, isRejected);
                    }
                }

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
