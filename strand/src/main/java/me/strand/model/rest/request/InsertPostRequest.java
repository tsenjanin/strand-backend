package me.strand.model.rest.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsertPostRequest {
    private String title;
    private String content;
    private Integer idTopic;
    private Boolean hidden;
    private Boolean locked;
}
