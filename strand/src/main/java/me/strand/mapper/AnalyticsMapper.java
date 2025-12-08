package me.strand.mapper;

import me.strand.model.llm.analytics.CommentAnalytics;
import me.strand.model.llm.analytics.PostAnalytics;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnalyticsMapper {
    List<PostAnalytics> getLastNPostsForAnalytics(Integer idUser, Integer limit);
    List<CommentAnalytics> getLastNCommentsForAnalytics(Integer idUser, Integer limit);
}
