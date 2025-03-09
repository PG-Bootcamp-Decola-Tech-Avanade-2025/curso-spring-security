package pg.decola_tech_avanade_2025.cursos.spring_security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pg.decola_tech_avanade_2025.cursos.spring_security.model.CustomUser;
import pg.decola_tech_avanade_2025.cursos.spring_security.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createUser(CustomUser newUser) {
        String password = newUser.getPassword();
        newUser.setPassword(passwordEncoder.encode(password));
        userRepository.save(newUser);
    }

    public List<CustomUser> getAll() {
        return userRepository.findAll();
    }
}
