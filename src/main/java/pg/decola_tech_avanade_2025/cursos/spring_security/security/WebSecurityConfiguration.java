package pg.decola_tech_avanade_2025.cursos.spring_security.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/*
    Como as classes apresentadas no curso estavam dpreciadas e, algumas,
    nem mais inclusas no Spring Security, optei por implementar segurança
    seguindo guias externos ao curso;
    Podem ser encontrados nos seguintes links:
        https://docs.spring.io/spring-security/reference/servlet/getting-started.html
        https://docs.spring.io/spring-security/reference/servlet/authorization/method-security.html

    * '{noop}' não faz parte das senhas; É um prefixo que define a estratégia de criptografia
    a ser utilizada.
*/

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfiguration {
    @Bean
    @ConditionalOnMissingBean(UserDetailsService.class)
    InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        return new InMemoryUserDetailsManager(
            User.withUsername("pedroghiotti")
                .password("{noop}caiju") // *
                .roles("USER")
                .build(),
            User.withUsername("adm_pedroghiotti")
                .password("{noop}adm_caiju")
                .roles("ADMIN", "USER")
                .build()
        );
    }

    @Bean
    @ConditionalOnMissingBean(AuthenticationEventPublisher.class)
    DefaultAuthenticationEventPublisher defaultAuthenticationEventPublisher(ApplicationEventPublisher delegate) {
        return new DefaultAuthenticationEventPublisher(delegate);
    }
}
