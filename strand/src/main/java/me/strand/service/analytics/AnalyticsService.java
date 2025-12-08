package me.strand.service.analytics;

import lombok.RequiredArgsConstructor;
import me.strand.error.ErrorCode;
import me.strand.error.ErrorProperties;
import me.strand.error.ErrorResponseBuilder;
import me.strand.exception.RestControllerException;
import me.strand.mapper.AnalyticsMapper;
import me.strand.model.llm.enums.AnalysisType;
import me.strand.model.llm.response.AnalyticsResponse;
import me.strand.service.llm.LLMProcesingService;
import me.strand.service.view.ViewService;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static me.strand.utils.constants.SystemVariables.*;
import static me.strand.utils.objectmapper.ObjectMapperUtils.*;

@Service
@RequiredArgsConstructor
public class AnalyticsService {
    private final ErrorProperties errorProperties;
    private final ErrorResponseBuilder errorResponseBuilder;
    private final AnalyticsMapper analyticsMapper;
    private final LLMProcesingService llmProcesingService;
    private final ViewService viewService;

    public AnalyticsResponse analyzeContent(Integer idUser, Integer limit, Integer idPost, AnalysisType type) {
        try {
            // TODO: add insert and logging for each analysis operation

            return switch (type) {
                case USER_POST_ANALYSIS -> {
                    var content = analyticsMapper.getLastNPostsForAnalytics(idUser, limit);
                    var result = processContent(content, CONTENT_ANALYTICS_INSTRUCTIONS);
                    result.setIdUser(idUser);

                    yield result;
                }
                case USER_COMMENT_ANALYSIS -> {
                    var content = analyticsMapper.getLastNCommentsForAnalytics(idUser, limit);
                    var result = processContent(content, CONTENT_ANALYTICS_INSTRUCTIONS);
                    result.setIdUser(idUser);

                    yield result;
                }
                case COMPLETE_POST_ANALYSIS -> {
                    var content = viewService.getPostDetails(idPost);
                    var result = processContent(content, POST_ANALYTICS_INSTRUCTIONS);
                    result.setIdPost(idPost);

                    yield result;
                }
            };
        } catch (IOException e) {
            throw new RestControllerException(errorResponseBuilder
                    .build(errorProperties.getError(ErrorCode.UNKNOWN_ANALYTICS_ERROR)));
        }
    }

    private AnalyticsResponse processContent(Object content, String instructions) throws IOException {
        return llmProcesingService.processContent(
                convertObjectToJson(content),
                instructions,
                GLOBAL_REASONING_EFFORT_HIGH,
                AnalyticsResponse.class
        );
    }
}
