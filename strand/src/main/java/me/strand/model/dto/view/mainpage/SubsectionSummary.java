package me.strand.model.dto.view.mainpage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubsectionSummary {
    private Integer idSubsection;
    private String title;
    List<SubtopicSummary> subtopics;
}
