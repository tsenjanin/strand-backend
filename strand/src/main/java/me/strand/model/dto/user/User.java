package me.strand.model.dto.user;

import lombok.Data;

import java.time.LocalDate;

@Data
public class User {
    private Integer idUser;
    private String username;
    private String password;
    private String email;
    private Boolean banned;
    private LocalDate muteDuration;
    private Integer idUserGroup;
}