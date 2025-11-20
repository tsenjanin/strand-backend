package me.strand.service.kafka;

import lombok.RequiredArgsConstructor;
import me.strand.service.llm.ModerationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaService {
    private final ModerationService moderationService;

    public void queueContent(String content) {
        // TODO: add check if AI moderations is on
        // TODO: if moderation is on continue queueing content
    }

    @Scheduled(fixedRate = 60000)
    public void processContent() {
        // TODO: process content and insert it to database or mark as rejected
    }
}
