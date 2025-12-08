package me.strand.mapper;

import me.strand.model.dto.view.post.PostCommentDetails;
import me.strand.model.rest.request.InsertCommentRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostCommentMapper {
    void insertComment(InsertCommentRequest request);
    List<PostCommentDetails> getPostCommentsForPost(Integer idPost);
}
