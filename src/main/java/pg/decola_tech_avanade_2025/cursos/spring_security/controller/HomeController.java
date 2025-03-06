package pg.decola_tech_avanade_2025.cursos.spring_security.controller;

import org.springframework.http.ResponseEntity;
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
}
