package me.strand.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.strand.service.auth.JwtService;
import me.strand.service.auth.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtTokenAuthorizationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
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

            if (accessToken != null && jwtService.isTokenExpired(accessToken.get())) {
                // TODO: var userDetails = userDetailsService.loadby

                response.addHeader("X-TOKEN", jwtService.generateJwtToken(
                        Map.of("Authorities", userDetails.getAuthorities())
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
