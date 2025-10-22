package com.product.config.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

/**
 * Clase de utilidades para JWT's
 */
@Component
public class JwtUtil {

    /**
     * Clave provisional
     * @alpha
     */
    @Value("${jwt.secret.key}")
	private static final String SECRET_KEY;

    /**
     * Clave secreta
     */
    private static final SecretKey secretKey = new SecretKeySpec(Base64.getDecoder().decode(SECRET_KEY), "HmacSHA256");

    /**
     * Regresa las claims o una cadena, al parsear el tóken
     * @param token tóken recibido
     * @return las claims o una cadena, al parsear el tóken
     */
    public Claims extractClaims(String token) {

        JwtParser jwtParser = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build();

        return jwtParser.parseClaimsJws(token).getBody();
    }

    /**
     * Regresa la claim <code>sub</code> proveniente del tóken
     * @param token tóken recibido
     * @return La claim <code>sub</code> proveniente del tóken
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Regresa la claim <code>roles</code> proveniente del tóken
     * @param token tóken recibido
     * @return La claim <code>roles</code> proveniente del tóken
     */
	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> extractPermisos(String token) {
        return extractClaims(token).get("roles", List.class);
    }

    /**
     * Valida un tóken
     * @param token tóken recibido
     * @param username nombre de usuario (sub)
     * @return <code>true</code> si la información corresponde y es válida, <code>false</code> si no lo es
     */
    public boolean isTokenValid(String token, String username) {
        return extractUsername(token).equals(username) && !isTokenExpired(token);
    }

    /**
     * Valida la fecha de expiración de un tóken con la fecha actual
     * @param token tóken recibido
     * @return <code>true</code> si el tóken no ha expirado, <code>false</code> si ya expiró
     */
    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    /**
     * Regresa una claim buscada proveniente del tóken
     * @param token tóken recibido
     * @return Una claim buscada proveniente del tóken
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(extractClaims(token));
    }
}
