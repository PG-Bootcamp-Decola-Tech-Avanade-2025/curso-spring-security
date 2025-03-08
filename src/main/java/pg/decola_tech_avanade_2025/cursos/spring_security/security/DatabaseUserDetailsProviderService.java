package pg.decola_tech_avanade_2025.cursos.spring_security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pg.decola_tech_avanade_2025.cursos.spring_security.model.CustomUser;
import pg.decola_tech_avanade_2025.cursos.spring_security.repository.UserRepository;

import java.util.stream.Collectors;

@Service
public class DatabaseUserDetailsProviderService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUser userEntity = userRepository.findByUsername(username);

        if (userEntity == null) {
            throw new UsernameNotFoundException(username);
        }

        return new User(
            userEntity.getUsername(),
            userEntity.getPassword(),
            userEntity.getRoles()
                .stream()
                .map(role ->
                        new SimpleGrantedAuthority("ROLE_" + role)).collect(Collectors.toSet()
                )
        );
    }
}
