package me.strand.model.llm.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnalyticsResponse {
    private Integer idUser;
    private Integer idPost;
    private String verdict;
    private String explanation;
}
