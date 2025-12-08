package me.strand.model.llm.analytics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostAnalytics {
    private Integer idPost;
    private String title;
    private String content;
    private Boolean locked;
    private Boolean hidden;
}
