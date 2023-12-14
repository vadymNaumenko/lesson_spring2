package spring.config;

import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import spring.domain.User;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtilAlt {

    private final String SECRET_KEY = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
    private final int TOKEN_VALIDITY_TIME = 10; // in min

    private final String TOKEN_HEADER = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";

    private final JwtParserBuilder jwtParser;

//    public JwtUtil() {
//        this.jwtParser = Jwts.parser().setSigningKey(SECRET_KEY);
//    }
public JwtUtilAlt() {
    this.jwtParser = Jwts.parser().setSigningKey(SECRET_KEY);
}
    public String createToken(User user) {
        Claims claims = (Claims) Jwts.claims().setSubject(user.getLogin());
        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(TOKEN_VALIDITY_TIME));
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(tokenValidity)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

//    public String createToken(User user) {
//        // мы получаем логин пользователя
//        ClaimsBuilder claimsBuilder = Jwts.claims().setSubject(user.getLogin());
//        //claims.put("authorities", user.getAuthorities());
//        // берем текущую дату
//        Date tokenCreateTime = new Date();
//        // к этой дате прибавляем 10 минут
//        Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(TOKEN_VALIDITY_TIME));
//        // создаем токен
//        return Jwts.builder()
//                .setClaims(claims) // в него кладем логин
//                .setExpiration(tokenValidity) // кладем дату, когда он перестанет быть валидным
//                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // подписываем его
//                .compact();
//    }

    public Claims resolveClaims(HttpServletRequest request) {
        try {
            String token = resolveToken(request);
            if (token != null) {
                return jwtParser.build().parseClaimsJws(token).getBody();
//                return jwtParser.parseClaimsJws(token).getBody();
            }
            return null;
        } catch (ExpiredJwtException ex) {
            request.setAttribute("expired", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            request.setAttribute("invalid", ex.getMessage());
            throw ex;
        }
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

    public boolean validateClaims(Claims claims) throws AuthenticationException {
        try {
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            throw e;
        }
    }
}
