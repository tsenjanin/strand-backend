package me.strand.controller;

import lombok.RequiredArgsConstructor;
import me.strand.model.rest.request.ContentType;
import me.strand.model.rest.request.InsertCommentRequest;
import me.strand.model.rest.response.MainPageContentResponse;
import me.strand.service.kafka.KafkaProducerService;
import me.strand.service.view.ViewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static me.strand.utils.constants.SystemVariables.KAFKA_TOPIC;

@RestController
@RequestMapping("view")
@RequiredArgsConstructor
@Validated
public class ViewController {
    private final ViewService viewService;

    @GetMapping("main-page")
    public ResponseEntity<MainPageContentResponse> getMainPageContent() {
        var content = viewService.getMainPageContent();

        return new ResponseEntity<>(content, HttpStatus.OK);
    }
}
