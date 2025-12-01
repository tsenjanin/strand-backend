package me.strand.model.rest.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.strand.model.dto.view.mainpage.AnnouncementSummary;
import me.strand.model.dto.view.mainpage.PostSummary;
import me.strand.model.dto.view.mainpage.SectionSummary;
import me.strand.model.dto.view.mainpage.StatisticsSummary;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MainPageContentResponse {
    private List<SectionSummary> sections;
    private List<AnnouncementSummary> announcements;
    private List<PostSummary> recentPosts;
    private StatisticsSummary statistics;
}
