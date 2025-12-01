package me.strand.model.dto.view.mainpage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsSummary {
    private Integer totalMembers;
    private Integer totalPosts;
}
