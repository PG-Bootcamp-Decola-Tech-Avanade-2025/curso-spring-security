package pg.decola_tech_avanade_2025.cursos.spring_security.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader(JwtCreator.HEADER_AUTHORIZATION);

        try {
            if (token == null || token.isBlank()) {
                SecurityContextHolder.clearContext();
            }
            JwtObject jwtObject = JwtCreator.create(token, SecurityConfig.PREFIX, SecurityConfig.KEY);

            List<SimpleGrantedAuthority> authorities = authorities(jwtObject.getRoles());

            UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(
                    jwtObject.getSubject(),
                    null,
                    authorities
            );

            SecurityContextHolder.getContext().setAuthentication(userToken);

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpStatus.FORBIDDEN.value());
        }
    }

    private List<SimpleGrantedAuthority> authorities(List<String> roles) {
        return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
