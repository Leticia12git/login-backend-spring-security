package leticia.edu.login_backend.controller;

import leticia.edu.login_backend.entity.User;
import leticia.edu.login_backend.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/login")

public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    private User login(@RequestBody User user) throws Exception {
       User newUser =  userService.login(user);
        return newUser;
    }
}
