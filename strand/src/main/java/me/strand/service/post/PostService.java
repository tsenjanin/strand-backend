package me.strand.service.post;

import lombok.RequiredArgsConstructor;
import me.strand.error.ErrorCode;
import me.strand.error.ErrorProperties;
import me.strand.error.ErrorResponseBuilder;
import me.strand.exception.ValidationException;
import me.strand.mapper.PostMapper;
import me.strand.model.rest.request.InsertPostRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final ErrorProperties errorProperties;
    private final ErrorResponseBuilder errorResponseBuilder;
    private final PostMapper postMapper;

    public void insertPost(InsertPostRequest insertPostRequest) {
        try {
            postMapper.insertPost(insertPostRequest);
        } catch (Exception e) {
            throw new ValidationException(
                    errorResponseBuilder.build(errorProperties.getError(ErrorCode.INVALID_POST_FORMAT))
            );
        }
    }
}
