package me.strand.controller;

import lombok.RequiredArgsConstructor;
import me.strand.service.kafka.KafkaProducerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static me.strand.utils.constants.SystemVariables.*;

@RestController
@RequiredArgsConstructor
public class KafkaController {
    private final KafkaProducerService kafkaProducerService;

    @PostMapping("/send")
    public String sendMessage(@RequestParam("message") String message) {
        kafkaProducerService.sendMessage(KAFKA_TOPIC, message);

        return "Message sent: " + message;
    }
}