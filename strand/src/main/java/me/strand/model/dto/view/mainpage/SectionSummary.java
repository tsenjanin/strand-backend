package me.strand.model.dto.view.mainpage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionSummary {
    private Integer idSection;
    private String title;
    private List<SubsectionSummary> subsections;
    private PostSummary mostRecentPost;
}
