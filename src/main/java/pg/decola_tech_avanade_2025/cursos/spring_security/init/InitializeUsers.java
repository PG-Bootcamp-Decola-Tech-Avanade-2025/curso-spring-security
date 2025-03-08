package pg.decola_tech_avanade_2025.cursos.spring_security.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pg.decola_tech_avanade_2025.cursos.spring_security.model.CustomUser;
import pg.decola_tech_avanade_2025.cursos.spring_security.repository.UserRepository;

import java.util.List;

@Component
public class InitializeUsers implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (userRepository.findByUsername("admin") == null) {
            CustomUser newUser = new CustomUser() {{
               setName("admin");
               setUsername("admin");
               setPassword("admin");
               setRoles(List.of("ADMIN", "USER"));
            }};
            userRepository.save(newUser);
        }

        if (userRepository.findByUsername("user") == null) {
            CustomUser newUser = new CustomUser() {{
                setName("user");
                setUsername("user");
                setPassword("user");
                setRoles(List.of("USER"));
            }};
            userRepository.save(newUser);
        }
    }
}
