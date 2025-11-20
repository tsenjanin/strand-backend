package me.strand.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.strand.service.auth.CustomUserDetailsService;
import me.strand.service.auth.JwtService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtTokenAuthorizationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;
    private final AuthUtils authUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        var authHeader = request.getHeader("Authorization");
        if (authHeader != null && jwtService.extractTokenFromHeader(authHeader).isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            var accessToken = jwtService.extractTokenFromHeader(authHeader);

            if (accessToken.isPresent() && !jwtService.isTokenExpired(accessToken.get())) {
                var parsedToken = jwtService.parseJwtToken(accessToken.get());
                var userDetails = userDetailsService.loadUserByUsername(parsedToken.getSubject());

                response.addHeader("X-TOKEN", jwtService.generateJwtToken(
                        Map.of("Authorities", userDetails.getAuthorities()),
                        userDetails.getUsername()
                ));

                authUtils.authenticate(userDetails);
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
