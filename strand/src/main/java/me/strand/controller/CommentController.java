package me.strand.controller;

import lombok.RequiredArgsConstructor;
import me.strand.model.rest.request.ContentType;
import me.strand.model.rest.request.InsertCommentRequest;
import me.strand.service.kafka.KafkaProducerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static me.strand.utils.constants.SystemVariables.*;

@RestController
@RequestMapping("comment")
@RequiredArgsConstructor
@Validated
public class CommentController {
    private final KafkaProducerService kafkaProducerService;

    @PostMapping
    public ResponseEntity<Void> insertComment(@ModelAttribute InsertCommentRequest insertCommentRequest) {
        kafkaProducerService.queueContent(KAFKA_TOPIC, ContentType.COMMENT, insertCommentRequest);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
