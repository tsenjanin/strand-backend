package me.strand.service.view;

import lombok.RequiredArgsConstructor;
import me.strand.error.ErrorCode;
import me.strand.error.ErrorProperties;
import me.strand.error.ErrorResponseBuilder;
import me.strand.exception.RestControllerException;
import me.strand.mapper.*;
import me.strand.model.dto.view.mainpage.PostSummary;
import me.strand.model.dto.view.mainpage.StatisticsSummary;
import me.strand.model.dto.view.mainpage.SubtopicSummary;
import me.strand.model.dto.view.post.PostDetails;
import me.strand.model.rest.response.MainPageContentResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ViewService {
    private final ErrorProperties errorProperties;
    private final ErrorResponseBuilder errorResponseBuilder;
    private final AnnouncementMapper announcementMapper;
    private final SectionMapper sectionMapper;
    private final SubsectionMapper subsectionMapper;
    private final StatisticsMapper statisticsMapper;
    private final SubtopicMapper subtopicMapper;
    private final PostMapper postMapper;
    private final PostCommentMapper postCommentMapper;

    public MainPageContentResponse getMainPageContent() {
        try {
            var totalPosts = statisticsMapper.getTotalPosts();
            var totalMembers = statisticsMapper.getTotalMembers();

            var statisticsSummary = new StatisticsSummary(totalPosts, totalMembers);
            var recentPosts = postMapper.getRecentPosts();
            var announcements = announcementMapper.getAnnouncementSummaries();

            var sections = sectionMapper.getSectionSummaries();

            sections.forEach(section -> {
                var subsections = subsectionMapper.getSubsectionSummariesForSection(section.getIdSection());
                subsections.forEach(subsection ->
                        subsection.setSubtopics(subtopicMapper.getSubtopicsForSubsection(subsection.getIdSubsection())));

                section.setSubsections(subsections);

                var mostRecentPost = postMapper.getMostRecentPostForSection(section.getIdSection());
                section.setMostRecentPost(mostRecentPost);
            });

            return MainPageContentResponse.builder()
                    .announcements(announcements)
                    .sections(sections)
                    .statistics(statisticsSummary)
                    .recentPosts(recentPosts)
                    .build();
        } catch (Exception e) {
            throw new RestControllerException(errorResponseBuilder
                    .build(errorProperties.getError(ErrorCode.TEMPORARY_CODE)));
        }
    }

    public List<SubtopicSummary> getSubsectionDetails(Integer idSubsection) {
        return subtopicMapper.getSubtopicsForSubsection(idSubsection);
    }

    public List<PostSummary> getSubtopicPosts(Integer idSubtopic) {
        return postMapper.getPostsForSubtopic(idSubtopic);
    }

    public PostDetails getPostDetails(Integer idPost) {
        try {
            var postDetails = postMapper.getPostDetails(idPost);
            var postComments = postCommentMapper.getPostCommentsForPost(idPost);
            postDetails.setComments(postComments);

            return postDetails;
        } catch (Exception e) {
            throw new RestControllerException(errorResponseBuilder
                    .build(errorProperties.getError(ErrorCode.TEMPORARY_CODE)));
        }
    }
}
