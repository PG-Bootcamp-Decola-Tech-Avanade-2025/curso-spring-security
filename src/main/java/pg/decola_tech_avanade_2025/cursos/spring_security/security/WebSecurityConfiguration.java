package pg.decola_tech_avanade_2025.cursos.spring_security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/*
    Como as classes apresentadas no curso estavam dpreciadas e, algumas,
    nem mais inclusas no Spring Security, optei por implementar segurança
    seguindo guias externos ao curso;
    Podem ser encontrados nos seguintes links:
        https://docs.spring.io/spring-security/reference/servlet/getting-started.html
        https://docs.spring.io/spring-security/reference/servlet/authorization/method-security.html
        https://docs.spring.io/spring-security/reference/servlet/configuration/java.html#_securityfilterchain_endpoints
        https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/basic.html
        https://www.youtube.com/watch?v=bOX1VYNqKCY

    * '{noop}' não faz parte das senhas; É um prefixo que define a estratégia de criptografia
    a ser utilizada.
*/

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfiguration {
    @Autowired
    private DatabaseUserDetailsProviderService databaseUserDetailsProviderService;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        daoAuthenticationProvider.setUserDetailsService(databaseUserDetailsProviderService);
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain h2FilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.securityMatcher("/h2-console/**")
            .authorizeHttpRequests(authorization -> authorization
                .anyRequest().permitAll()
            ).build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.securityMatcher("/user/**", "/admin/**")
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(authorization -> authorization
                .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            ).httpBasic(Customizer.withDefaults()).build();
    }
}
