package me.strand.service.auth;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService {

    // TODO: add user repository and pull out the user, pass roles and the username to CustomUserDetails...

    public CustomUserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
        return null;
    }
}
