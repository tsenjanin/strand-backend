package me.strand.model.rest.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsertPostCommentRequest {
    private String content;
    private Integer idPost;
    private Integer idUser;
}
