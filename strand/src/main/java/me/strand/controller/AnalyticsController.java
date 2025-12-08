package me.strand.controller;

import lombok.RequiredArgsConstructor;
import me.strand.model.llm.enums.AnalysisType;
import me.strand.model.llm.response.AnalyticsResponse;
import me.strand.service.analytics.AnalyticsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("analytics")
@RequiredArgsConstructor
public class AnalyticsController {
    private final AnalyticsService analyticsService;

    @GetMapping("content")
    public ResponseEntity<AnalyticsResponse> analyseContent(Integer idUser, Integer limit, AnalysisType type) {
        var response = analyticsService.analyzeContent(idUser, limit, null, type);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("post")
    public ResponseEntity<AnalyticsResponse> analysePost(Integer idPost, AnalysisType type) {
        var response = analyticsService.analyzeContent(null, null, idPost, type);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
