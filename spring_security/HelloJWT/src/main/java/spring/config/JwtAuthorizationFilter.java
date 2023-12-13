package spring.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ObjectMapper mapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            // из запроса вытаскиваем токен
            String accessToken = jwtUtil.resolveToken(request);
            if (accessToken == null) { // если токена нет - отменяем работу
                filterChain.doFilter(request, response);
                return;
            }
            System.out.println("token : " + accessToken);
            // если токен есть
            // claims - составные части payload
            Claims claims = jwtUtil.resolveClaims(request);
            // проверяем этот токен
            if (claims != null & jwtUtil.validateClaims(claims)) {
                // получаем логин
                String login = claims.getSubject();
                //Collection<Map> authoryListMap = (Collection<Map>) claims.get("authorities");
                System.out.println("login : " + login);

                //Set<GrantedAuthority> authorities = new HashSet<>();
                //authoryListMap.forEach(item -> authorities.add(new SimpleGrantedAuthority((String) item.get("authority"))));
                // говорим, что все окей - запрос разрешен
                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(login, "", new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            // если будут какие-то ошибки с токеном (например, его изменили)
            Map<String, Object> errorDetails = new HashMap<>();
            errorDetails.put("message", "Authentication Error");
            errorDetails.put("details", e.getMessage());
            // мы говорим, что действие запрещено
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            mapper.writeValue(response.getWriter(), errorDetails);
        }
        filterChain.doFilter(request, response);
    }
}
