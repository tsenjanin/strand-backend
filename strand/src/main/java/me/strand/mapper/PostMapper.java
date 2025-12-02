package me.strand.mapper;

import me.strand.model.dto.view.mainpage.PostSummary;
import me.strand.model.dto.view.post.PostDetails;
import me.strand.model.rest.request.InsertPostRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
    void insertPost(InsertPostRequest request);
    List<PostSummary> getRecentPosts();
    List<PostSummary> getPostsForSubtopic(Integer idSubtopic);
    PostSummary getMostRecentPostForSection(Integer idSection);
    PostDetails getPostDetails(Integer idPost);
}
