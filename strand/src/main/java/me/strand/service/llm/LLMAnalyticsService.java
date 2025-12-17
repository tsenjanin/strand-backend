package me.strand.service.llm;

import lombok.RequiredArgsConstructor;
import me.strand.error.ErrorCode;
import me.strand.error.ErrorProperties;
import me.strand.error.ErrorResponseBuilder;
import me.strand.exception.RestControllerException;
import me.strand.mapper.AnalyticsMapper;
import me.strand.model.llm.response.AnalyticsResponse;
import me.strand.model.rest.request.AnalyzeContentRequest;
import me.strand.service.view.ViewService;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static me.strand.utils.constants.SystemVariables.*;
import static me.strand.utils.objectmapper.ObjectMapperUtils.*;

@Service
@RequiredArgsConstructor
public class LLMAnalyticsService {
    private final ErrorProperties errorProperties;
    private final ErrorResponseBuilder errorResponseBuilder;
    private final AnalyticsMapper analyticsMapper;
    private final LLMProcessingService llmProcessingService;
    private final ViewService viewService;

    public AnalyticsResponse analyzeContent(AnalyzeContentRequest request) {
        try {
            return switch (request.getType()) {
                case USER_POST_ANALYSIS -> {
                    var content = analyticsMapper.getLastNPostsForAnalytics(request.getIdUser(), request.getLimit());
                    var result = processContent(content, CONTENT_ANALYTICS_INSTRUCTIONS);
                    result.setIdUser(request.getIdUser());

                    analyticsMapper.insertPostingBehaviourAnalysis(result);

                    yield result;
                }
                case USER_COMMENT_ANALYSIS -> {
                    var content = analyticsMapper.getLastNCommentsForAnalytics(request.getIdUser(), request.getLimit());
                    var result = processContent(content, CONTENT_ANALYTICS_INSTRUCTIONS);
                    result.setIdUser(request.getIdUser());

                    analyticsMapper.insertCommentingBehaviourAnalysis(result);

                    yield result;
                }
                case COMPLETE_POST_ANALYSIS -> {
                    var content = viewService.getPostDetails(request.getIdPost());
                    var result = processContent(content, POST_ANALYTICS_INSTRUCTIONS);
                    result.setIdPost(request.getIdPost());

                    analyticsMapper.insertCompletePostBehaviourAnalysis(result);

                    yield result;
                }
            };
        } catch (IOException e) {
            throw new RestControllerException(errorResponseBuilder
                    .build(errorProperties.getError(ErrorCode.UNKNOWN_ANALYTICS_ERROR)));
        }
    }

    private AnalyticsResponse processContent(Object content, String instructions) throws IOException {
        return llmProcessingService.processContent(
                convertObjectToJson(content),
                instructions,
                GLOBAL_REASONING_EFFORT_HIGH,
                AnalyticsResponse.class
        );
    }
}
