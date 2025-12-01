package me.strand.model.dto.view.mainpage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostSummary {
    private Integer idPost;
    private String title;
    private String originalPoster;
}
