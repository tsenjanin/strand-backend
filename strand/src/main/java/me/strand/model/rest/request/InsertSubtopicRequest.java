package me.strand.model.rest.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsertSubtopicRequest {
    private String title;
    private String description;
    private Integer idSection;
    private Integer idSubsection;
}
