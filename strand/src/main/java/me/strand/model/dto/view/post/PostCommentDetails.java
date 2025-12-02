package me.strand.model.dto.view.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCommentDetails {
    private Integer idPostComment;
    private String content;
    private LocalDateTime tstamp;
    private Integer idUser;
    private String originalPoster;
    private Integer idPost;
}
