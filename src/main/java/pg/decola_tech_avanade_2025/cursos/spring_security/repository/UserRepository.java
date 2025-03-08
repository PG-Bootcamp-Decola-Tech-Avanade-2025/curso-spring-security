package pg.decola_tech_avanade_2025.cursos.spring_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pg.decola_tech_avanade_2025.cursos.spring_security.model.CustomUser;

@Repository
public interface UserRepository extends JpaRepository<CustomUser, Integer> {
    public CustomUser findByUsername(String username);
}
