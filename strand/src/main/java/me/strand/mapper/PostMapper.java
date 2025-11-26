package me.strand.mapper;

import me.strand.model.rest.request.InsertPostRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostMapper {
    void insertPost(InsertPostRequest request);
}
