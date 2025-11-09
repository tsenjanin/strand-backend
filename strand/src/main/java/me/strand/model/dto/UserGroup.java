package me.strand.model.dto;

import lombok.Data;

@Data
public class UserGroup {
    private Integer id;
    private String name;
    private String description;
    private Boolean isActive;
}