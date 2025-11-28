package me.strand.service.comment;

import lombok.RequiredArgsConstructor;
import me.strand.error.ErrorCode;
import me.strand.error.ErrorProperties;
import me.strand.error.ErrorResponseBuilder;
import me.strand.exception.ValidationException;
import me.strand.mapper.CommentMapper;
import me.strand.model.rest.request.InsertCommentRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final ErrorProperties errorProperties;
    private final ErrorResponseBuilder errorResponseBuilder;
    private final CommentMapper commentMapper;

    @Transactional
    public void insertComment(InsertCommentRequest insertCommentRequest) {
        try {
            commentMapper.insertComment(insertCommentRequest);
        } catch (Exception e) {
            throw new ValidationException(
                    errorResponseBuilder.build(errorProperties.getError(ErrorCode.COMMENT_INSERTION_FAILED))
            );
        }
    }
}
