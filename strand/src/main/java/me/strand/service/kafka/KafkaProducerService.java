package me.strand.service.kafka;

import lombok.RequiredArgsConstructor;
import me.strand.error.ErrorCode;
import me.strand.error.ErrorProperties;
import me.strand.error.ErrorResponseBuilder;
import me.strand.exception.RestControllerException;
import me.strand.model.rest.request.ContentType;
import me.strand.model.rest.request.InsertCommentRequest;
import me.strand.model.rest.request.InsertPostRequest;
import me.strand.model.rest.request.ModerationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponse;

import java.io.IOException;

import static me.strand.utils.mapper.ObjectMapperUtils.*;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);
    private final ErrorProperties errorProperties;
    private final ErrorResponseBuilder errorResponseBuilder;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public <T> void queueContent(String topic, ContentType contentType, T request) {
        try {
            if (request instanceof InsertCommentRequest || request instanceof InsertPostRequest) {
                var moderationRequest = switch (contentType) {
                    case POST -> new ModerationRequest(ContentType.POST.name(), convertObjectToJson(request));
                    case COMMENT -> new ModerationRequest(ContentType.COMMENT.name(), convertObjectToJson(request));
                };

                kafkaTemplate.send(topic, convertObjectToJson(moderationRequest));

                logger.info("Sent moderation request: '{}' to '{}'.", moderationRequest, topic);
                return;
            }

            throw new RestControllerException(
                    errorResponseBuilder.build(errorProperties.getError(ErrorCode.CLASS_NOT_SUPPORTED))
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
