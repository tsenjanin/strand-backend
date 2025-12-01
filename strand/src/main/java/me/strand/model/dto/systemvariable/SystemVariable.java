package me.strand.model.dto.systemvariable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemVariable {
    private String name;
    private String value;
}
