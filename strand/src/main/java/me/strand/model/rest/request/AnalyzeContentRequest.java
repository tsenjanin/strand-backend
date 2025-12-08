package me.strand.model.rest.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.strand.model.llm.enums.AnalysisType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnalyzeContentRequest {
    private Integer idUser;
    private Integer idPost;
    private Integer limit;
    private AnalysisType type;
}
