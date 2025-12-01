package me.strand.service.kafka;

import lombok.RequiredArgsConstructor;
import me.strand.model.rest.request.InsertCommentRequest;
import me.strand.model.rest.request.InsertPostRequest;
import me.strand.model.rest.request.ModerationRequest;
import me.strand.service.comment.CommentService;
import me.strand.service.post.PostService;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static me.strand.utils.objectmapper.ObjectMapperUtils.convertJsonToObject;

@Component
@RequiredArgsConstructor
public class KafkaUtils {
    private final PostService postService;
    private final CommentService commentService;

    public void insertContent(ModerationRequest request, Boolean... params) throws IOException {
        var isRejected = params.length > 0 ? params[0] : false;

        switch (request.getType()) {
            case "POST" -> {
                InsertPostRequest postRequest =
                        convertJsonToObject(request.getData(), InsertPostRequest.class);
                postRequest.setHidden(isRejected);
                postRequest.setLocked(isRejected);

                postService.insertPost(postRequest);
            }
            case "COMMENT" -> {
                InsertCommentRequest commentRequest =
                        convertJsonToObject(request.getData(), InsertCommentRequest.class);
                commentRequest.setHidden(isRejected);

                commentService.insertComment(commentRequest);
            }
        }
    }
}
