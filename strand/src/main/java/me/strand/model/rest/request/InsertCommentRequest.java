package me.strand.model.rest.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsertCommentRequest {
    private String content;
    private Integer idPost;
    private Boolean hidden;
}
