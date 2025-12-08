package me.strand.model.llm.analytics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentAnalytics {
    private Integer idPostComment;
    private String content;
    private Boolean hidden;
}
