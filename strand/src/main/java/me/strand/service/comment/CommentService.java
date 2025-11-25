package me.strand.service.comment;

import lombok.RequiredArgsConstructor;
import me.strand.model.rest.request.InsertCommentRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    public void insertComment(InsertCommentRequest insertCommentRequest, Boolean hidden) {
        // TODO
    }
}
