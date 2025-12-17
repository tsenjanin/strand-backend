package me.strand.model.rest.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSubsectionRequest {
    private Integer idSubsection;
    private Integer idSection;
    private String title;
    private String description;
}
