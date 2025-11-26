package me.strand.mapper;

import me.strand.model.rest.request.InsertCommentRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {
    void insertComment(InsertCommentRequest request);
}
