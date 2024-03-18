package br.com.wells.usuario.app.infrastructure.config.spring.security.jwt.impl;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.UUID;

import br.com.wells.usuario.app.infrastructure.config.spring.app.WellsUsuarioAppProperties;
import br.com.wells.usuario.app.infrastructure.config.spring.security.jwt.JWTToken;
import br.com.wells.usuario.app.infrastructure.config.spring.security.jwt.exception.TokenGenerationException;
import br.com.wells.usuario.app.infrastructure.config.spring.security.jwt.exception.TokenValidationException;
import br.com.wells.usuario.app.infrastructure.config.spring.security.user.UsuarioCustomDetails;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTTokenImpl implements JWTToken {

	public static final String JWT_BEARER = "Bearer ";

	public static final long EXPIRE_DAYS = 0;

	public static final long EXPIRE_HOURS = 0;

	public static final long EXPIRE_MINUTES = 30;

	private final Algorithm algorithm;

	private final WellsUsuarioAppProperties wellsUsuarioAppProperties;

	private final JWTVerifier verifier;

	public JWTTokenImpl(RSAPublicKey publicKey, RSAPrivateKey privateKey,
			WellsUsuarioAppProperties wellsUsuarioAppProperties) {
		this.wellsUsuarioAppProperties = wellsUsuarioAppProperties;

		this.algorithm = Algorithm.RSA256(publicKey, privateKey);
		this.verifier = JWT.require(algorithm).withIssuer(wellsUsuarioAppProperties.getName()).build();
	}

	private static String getClaim(DecodedJWT decodedJWT, String claimName) {
		Claim claim = decodedJWT.getClaim(claimName);
		return claim != null ? claim.asString() : null;
	}

	private String refactorToken(String token) {
		if (token.contains(JWT_BEARER)) {
			return token.substring(JWT_BEARER.length());
		}
		return token;
	}

	@Override
	public Boolean verifyJWT(String jwtToken) {
		boolean tokenValido = true;
		try {
			verifier.verify(jwtToken);
		}
		catch (JWTVerificationException e) {
			tokenValido = false;
		}
		return tokenValido;
	}

	@Override
	public boolean isJWTExpired(String jwtToken) {
		boolean tokenExpired = false;
		try {
			DecodedJWT decodedJWT = verifier.verify(jwtToken);
			Date expiresAt = decodedJWT.getExpiresAt();

			tokenExpired = expiresAt.getTime() < System.currentTimeMillis();
		}
		catch (JWTVerificationException e) {
			tokenExpired = true;
		}
		return tokenExpired;
	}

	@Override
	public String generateToken(final UsuarioCustomDetails usuario) {
		Date issuedAt = Date.from(Instant.now());
		Instant limit = genExpirationDate(issuedAt);
		try {

			return JWT.create()
				.withHeader(Collections.singletonMap("typ", "JWT"))
				.withIssuer(this.wellsUsuarioAppProperties.getName())
				.withClaim(usuario.getRolesMapKey(), usuario.getRolesMap().get(usuario.getRolesMapKey()))
				.withIssuedAt(issuedAt)
				.withSubject(usuario.getUsername())
				.withExpiresAt(limit)
				.withJWTId(UUID.randomUUID().toString())
				.withNotBefore(new Date(System.currentTimeMillis() + 1000L))
				.sign(algorithm);

		}
		catch (JWTCreationException exception) {
			throw new TokenGenerationException("Erro ao gerar o token", exception);
		}
	}

	@Override
	public String validateToken(String token) {
		try {
			String cleanedToken = refactorToken(token);

			return JWT.require(algorithm).withIssuer("auth-api").build().verify(cleanedToken).getSubject();
		}
		catch (TokenExpiredException expiredException) {
			throw new TokenValidationException("O token expirou", expiredException);
		}
		catch (JWTVerificationException exception) {
			throw new TokenValidationException("Erro ao validar o token", exception);
		}
	}

	private Instant genExpirationDate(Date start) {
		LocalDateTime dateTime = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		LocalDateTime end = dateTime.plusDays(EXPIRE_DAYS).plusHours(EXPIRE_HOURS).plusMinutes(EXPIRE_MINUTES);

		return end.atZone(ZoneId.systemDefault()).toInstant();
	}

}
