package pg.decola_tech_avanade_2025.cursos.spring_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pg.decola_tech_avanade_2025.cursos.spring_security.model.CustomUser;
import pg.decola_tech_avanade_2025.cursos.spring_security.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<CustomUser> getAll() {
        return userService.getAll();
    }

    @PostMapping
    public void create(@RequestBody CustomUser newUser) {
        userService.createUser(newUser);
    }
}
