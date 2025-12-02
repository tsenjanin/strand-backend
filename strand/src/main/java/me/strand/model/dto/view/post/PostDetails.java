package me.strand.model.dto.view.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDetails {
    private Integer idPost;
    private String title;
    private String content;
    private Boolean locked;
    private Integer idSubtopic;
    private Integer idUser;
    private String originalPoster;
    private LocalDateTime tstamp;
    private List<PostCommentDetails> comments;
}
