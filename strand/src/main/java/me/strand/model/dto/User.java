package me.strand.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class User {
    private UUID id;
    private String username;
    private String email;
    private byte[] hashedPassword;
    private byte[] salt;
    private Boolean banned;
    private LocalDate muteDuration;
    private Integer idUserGroup;
}