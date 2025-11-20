package me.strand.controller;

import lombok.RequiredArgsConstructor;
import me.strand.model.auth.JwtTokenDto;
import me.strand.model.rest.request.UserLoginRequest;
import me.strand.service.auth.CustomUserDetailsService;
import me.strand.service.auth.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Validated
public class AuthController {

    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    @PostMapping
    public ResponseEntity<JwtTokenDto> login(@RequestBody UserLoginRequest userLoginRequest) {
        var user = customUserDetailsService.loadUserByUsernameAndPassword(
                userLoginRequest.getUsername(),
                userLoginRequest.getPassword()
        );

        if (user != null) {
            var token = jwtService.generateJwtToken(
                    Map.of("Authorities", user.getAuthorities()),
                    user.getUsername()
            );

            return new ResponseEntity<>(new JwtTokenDto(token), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
