package me.strand.filter;

import lombok.RequiredArgsConstructor;
import me.strand.service.auth.UserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

//@Component
//@RequiredArgsConstructor
//public class AuthUtils {
//    public void authenticate(UserDetails userDetails) {
//        var authDetails = new UsernamePasswordAuthenticationToken(
//                userDetails,
//                null,
//                userDetails.getAuthorities()
//        );
//
//        var context = SecurityContextHolder.getContext();
//        context.setAuthentication(authDetails);
//        SecurityContextHolder.setContext(context);
//    }
//}
