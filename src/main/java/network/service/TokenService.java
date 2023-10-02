package network.service;

import network.dto.TokenModel;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class TokenService {

    private final JwtEncoder jwtEncoder;

    public TokenService(JwtEncoder jwtEncoder) {
        super();
        this.jwtEncoder = jwtEncoder;
    }

    public TokenModel generateAccessToken(String userName) {
        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(30, ChronoUnit.MINUTES))
                .subject(userName)
                .claim("scope", "[read]")
                .build();
        return new TokenModel(jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue());
    }
}
