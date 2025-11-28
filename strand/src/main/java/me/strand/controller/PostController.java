package me.strand.controller;

import lombok.RequiredArgsConstructor;
import me.strand.model.rest.request.ContentType;
import me.strand.model.rest.request.InsertPostRequest;
import me.strand.service.kafka.KafkaProducerService;
import me.strand.service.post.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static me.strand.utils.constants.SystemVariables.KAFKA_TOPIC;
import static me.strand.utils.constants.SystemVariables.LLM_MODERATION_ENABLED;

@RestController
@RequestMapping("post")
@RequiredArgsConstructor
@Validated
public class PostController {
    private final KafkaProducerService kafkaProducerService;

    @PostMapping
    public ResponseEntity<Void> insertPost(@ModelAttribute InsertPostRequest insertPostRequest) {
        kafkaProducerService.queueContent(KAFKA_TOPIC, ContentType.POST, insertPostRequest);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
