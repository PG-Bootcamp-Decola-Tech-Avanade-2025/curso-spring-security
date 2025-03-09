package pg.decola_tech_avanade_2025.cursos.spring_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pg.decola_tech_avanade_2025.cursos.spring_security.dto.LoginDto;
import pg.decola_tech_avanade_2025.cursos.spring_security.dto.SessionDto;
import pg.decola_tech_avanade_2025.cursos.spring_security.model.CustomUser;
import pg.decola_tech_avanade_2025.cursos.spring_security.repository.UserRepository;
import pg.decola_tech_avanade_2025.cursos.spring_security.security.JwtCreator;
import pg.decola_tech_avanade_2025.cursos.spring_security.security.JwtObject;
import pg.decola_tech_avanade_2025.cursos.spring_security.security.SecurityConfig;

import java.util.Date;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public SessionDto doLogin(@RequestBody LoginDto loginDto) throws Exception {
        CustomUser user = userRepository.findByUsername(loginDto.getUsername());

        if (user == null) {
            throw new Exception("Credenciais inválidas");
        }

        boolean isPasswordValid = passwordEncoder.matches(loginDto.getPassword(), user.getPassword());

        if (!isPasswordValid) {
            throw new Exception("Credenciais inválidas");
        }

        SessionDto sessionDto = new SessionDto();
        sessionDto.setLogin(user.getUsername());

        JwtObject jwtObject = new JwtObject();
        jwtObject.setIssuedAt(new Date());
        jwtObject.setExpiresAt(new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION));
        jwtObject.setRoles(user.getRoles());

        sessionDto.setToken(JwtCreator.create(SecurityConfig.PREFIX, SecurityConfig.KEY, jwtObject));

        return sessionDto;
    }
}
