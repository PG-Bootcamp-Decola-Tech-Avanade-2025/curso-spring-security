package pg.decola_tech_avanade_2025.cursos.spring_security.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pg.decola_tech_avanade_2025.cursos.spring_security.model.CustomUser;
import pg.decola_tech_avanade_2025.cursos.spring_security.repository.UserRepository;
import pg.decola_tech_avanade_2025.cursos.spring_security.service.UserService;

import java.util.List;

@Component
public class InitializeUsers implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (userRepository.findByUsername("admin") == null) {
            CustomUser newUser = new CustomUser();
            newUser.setName("admin");
            newUser.setUsername("admin");
            newUser.setPassword("admin");
            newUser.setRoles(List.of("ADMIN", "USER"));
            userService.createUser(newUser);
        }

        if (userRepository.findByUsername("user") == null) {
            CustomUser newUser = new CustomUser();
            newUser.setName("user");
            newUser.setUsername("user");
            newUser.setPassword("user");
            newUser.setRoles(List.of("USER"));
            userService.createUser(newUser);
        }
    }
}
