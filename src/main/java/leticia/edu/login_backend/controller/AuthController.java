package leticia.edu.login_backend.controller;

import leticia.edu.login_backend.dto.LoginDTO;
import leticia.edu.login_backend.entity.User;
import leticia.edu.login_backend.security.JwtToken;
import leticia.edu.login_backend.security.TokenUtil;
import leticia.edu.login_backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtToken> Login(@RequestBody LoginDTO login) {
        if (login.login().equals("") && login.password().equals("1234")) {
            JwtToken tk = TokenUtil.encode(login);
            if (tk != null) {
                return ResponseEntity.ok(tk);
            }
        }
        return ResponseEntity.status(403).build();
    }

    @PostMapping("/users")
    public ResponseEntity<User> addNewUser(@RequestBody User user) throws Exception {
        return ResponseEntity.status(201).body(userService.create(user));
    }
}