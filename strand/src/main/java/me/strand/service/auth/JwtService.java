package me.strand.service.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import me.strand.utils.Constants;
import me.strand.utils.EnvironmentConstants;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final Environment env;

    // TODO: fix expiration date and issuedAt
    public String generateJwtToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .issuer(Constants.ISSUER)
                .subject(subject)
                .expiration(new Date())
                .issuedAt(new Date())
                .signWith(getSecretKey(), Jwts.SIG.HS512)
                .claims(claims)
                .compact();

    }

    public Claims parseJwtToken(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // TODO: add secret key to environment variables
    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(env.getProperty(EnvironmentConstants.JWT_SECRET_KEY)));
    }

    // TODO: date generation utils needed
    public Boolean isTokenExpired(String token) {
        var claims = parseJwtToken(token);

        return claims.getExpiration().before(new Date());
    }

    public Optional<String> extractTokenFromHeader(String header) {
        if (header == null || !header.startsWith("Bearer ")) {
            return Optional.empty();
        }

        var token = header.substring(7).trim();
        return Optional.of(token);
    }
}