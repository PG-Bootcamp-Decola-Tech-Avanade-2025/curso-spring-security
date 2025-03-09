package pg.decola_tech_avanade_2025.cursos.spring_security.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.security.SignatureException;
import java.util.List;
import java.util.stream.Collectors;

public class JwtCreator {
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String ROLES_AUTHORITIES = "authorities";

    public static String create(String prefix, String key, JwtObject jwtObject) {
        String token = Jwts.builder()
                .subject(jwtObject.getSubject())
                .issuedAt(jwtObject.getIssuedAt())
                .expiration(jwtObject.getExpiresAt())
                .claim(ROLES_AUTHORITIES, checkRoles(jwtObject.getRoles()))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();

        return String.format("%s %s", prefix, token);
    }

    public static JwtObject create(String token, String prefix, String key)
            throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException {
        JwtObject jwtObject = new JwtObject();

        byte[] keyBytes = Decoders.BASE64.decode(key);
        SecretKey keyObject = Keys.hmacShaKeyFor(keyBytes);

        token = token.replace(prefix, "").replace(" ", "");

        Claims claims = Jwts.parser().verifyWith(keyObject).build().parseSignedClaims(token).getPayload();

        jwtObject.setSubject(claims.getSubject());
        jwtObject.setExpiresAt(claims.getExpiration());
        jwtObject.setIssuedAt(claims.getIssuedAt());
        jwtObject.setRoles((List<String>) claims.get(ROLES_AUTHORITIES));

        return jwtObject;
    }

    public static List<String> checkRoles(List<String> roles) {
        return roles.stream().map(s -> "ROLE_".concat(s.replaceAll("ROLE_", ""))).collect(Collectors.toList());
    }
}
