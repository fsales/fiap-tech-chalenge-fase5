package br.com.wells.app.usecases.security.impl;

import br.com.wells.app.infrastructure.spring.config.app.WellsUsuarioAppProperties;
import br.com.wells.app.infrastructure.spring.config.security.UsuarioCustomDetails;
import br.com.wells.app.usecases.security.TokenUserCase;
import br.com.wells.app.usecases.security.exception.TokenGenerationException;
import br.com.wells.app.usecases.security.exception.TokenValidationException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class TokenUserCaseImpl implements TokenUserCase {

    private final WellsUsuarioAppProperties wellsUsuarioAppProperties;

    public static final long EXPIRE_DAYS = 0;
    public static final long EXPIRE_HOURS = 0;
    public static final long EXPIRE_MINUTES = 30;

    public TokenUserCaseImpl(
            WellsUsuarioAppProperties wellsUsuarioAppProperties
    ) {
        this.wellsUsuarioAppProperties = wellsUsuarioAppProperties;
    }

    @Override
    public String generateToken(
            final UsuarioCustomDetails usuario
    ) {
        Date issuedAt = Date.from(Instant.now());
        Instant limit = genExpirationDate(issuedAt);
        try {
            Algorithm algorithm = Algorithm.HMAC256(
                    wellsUsuarioAppProperties.getApi().security().getToken().secret()
            );

            return JWT.create()
                    .withIssuer("auth-api")
                    .withIssuedAt(
                            issuedAt
                    )
                    .withSubject(
                            usuario.getUsername()
                    )
                    .withExpiresAt(
                            limit
                    )
                    .sign(algorithm);

        } catch (JWTCreationException exception) {
            throw new TokenGenerationException(
                    "Erro ao gerar o token",
                    exception
            );
        }
    }

    @Override
    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(
                    wellsUsuarioAppProperties.getApi().security().getToken().secret()
            );
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (TokenExpiredException expiredException) {
            throw new TokenValidationException(
                    "O token expirou",
                    expiredException
            );
        } catch (JWTVerificationException exception) {
            throw new TokenValidationException(
                    "Erro ao validar o token",
                    exception
            );
        }
    }

    private Instant genExpirationDate(Date start) {
        LocalDateTime dateTime = start.toInstant().atZone(
                ZoneId.systemDefault()
        ).toLocalDateTime();
        LocalDateTime end = dateTime.plusDays(
                EXPIRE_DAYS
        ).plusHours(
                EXPIRE_HOURS
        ).plusMinutes(
                EXPIRE_MINUTES
        );

        return end.atZone(
                ZoneId.systemDefault()
        ).toInstant();
    }
}
