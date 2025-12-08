package me.strand.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import me.strand.model.llm.response.AnalyticsResponse;
import me.strand.model.rest.request.AnalyzeContentRequest;
import me.strand.service.auth.CustomUserDetails;
import me.strand.service.llm.LLMAnalyticsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("analytics")
@RequiredArgsConstructor
public class AnalyticsController {
    private final LLMAnalyticsService llmAnalyticsService;

    @GetMapping("content")
    public ResponseEntity<AnalyticsResponse> analyseContent(@ModelAttribute AnalyzeContentRequest analyzeContentRequest,
                                                            @AuthenticationPrincipal CustomUserDetails userDetails,
                                                            HttpServletRequest request) {
        var response = llmAnalyticsService.analyzeContent(analyzeContentRequest);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
