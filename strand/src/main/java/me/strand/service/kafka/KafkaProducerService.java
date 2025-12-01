package me.strand.service.kafka;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import me.strand.error.ErrorCode;
import me.strand.error.ErrorProperties;
import me.strand.error.ErrorResponseBuilder;
import me.strand.exception.RestControllerException;
import me.strand.exception.ValidationException;
import me.strand.model.rest.request.ContentType;
import me.strand.model.rest.request.InsertCommentRequest;
import me.strand.model.rest.request.InsertPostRequest;
import me.strand.model.rest.request.ModerationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static me.strand.utils.constants.SystemVariables.*;
import static me.strand.utils.objectmapper.ObjectMapperUtils.*;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private final Logger log = LoggerFactory.getLogger(KafkaProducerService.class);
    private final ErrorProperties errorProperties;
    private final ErrorResponseBuilder errorResponseBuilder;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaUtils kafkaUtils;

    @SneakyThrows
    public <T> void queueContent(String topic, ContentType contentType, T request) {
        try {
            if (request instanceof InsertCommentRequest || request instanceof InsertPostRequest) {
                var moderationRequest = switch (contentType) {
                    case POST -> new ModerationRequest(ContentType.POST.name(), convertObjectToJson(request));
                    case COMMENT -> new ModerationRequest(ContentType.COMMENT.name(), convertObjectToJson(request));
                };

                if (LLM_MODERATION_ENABLED) {
                    kafkaTemplate.send(topic, convertObjectToJson(moderationRequest));

                    log.info("Sent moderation request: '{}' to '{}'.", moderationRequest, topic);
                    return;
                }

                kafkaUtils.insertContent(moderationRequest, false);
                return;
            }

            throw new RestControllerException(
                    errorResponseBuilder.build(errorProperties.getError(ErrorCode.CLASS_NOT_SUPPORTED))
            );
        } catch (IOException | RestControllerException | ValidationException e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }
}
