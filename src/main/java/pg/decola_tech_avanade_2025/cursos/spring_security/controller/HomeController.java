package pg.decola_tech_avanade_2025.cursos.spring_security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public ResponseEntity<Void> home() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("user")
    public ResponseEntity<Void> getUser() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("admin")
    public ResponseEntity<Void> getAdmin() {
        return ResponseEntity.ok().build();
    }
}
