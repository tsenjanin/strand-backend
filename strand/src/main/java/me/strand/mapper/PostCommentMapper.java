package me.strand.mapper;

import me.strand.model.dto.view.post.PostCommentDetails;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostCommentMapper {
    List<PostCommentDetails> getPostCommentsForPost(Integer idPost);
}
