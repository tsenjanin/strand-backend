package me.strand.model.rest.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsertSubsectionRequest {
    private String title;
    private String description;
    private Integer idSection;
}
