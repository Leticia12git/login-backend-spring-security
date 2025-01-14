package leticia.edu.login_backend.service;

import leticia.edu.login_backend.entity.User;
import leticia.edu.login_backend.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional findById(Long id) {
        return userRepository.findById(id);
    }

    public User login(User user) throws Exception {
        if (user.getId() != null) {
            throw new Exception("Este usuario ja existe");
        }
        userRepository.save(user);
        System.out.println("Usuario salvo " + user.getId());
        return user;
    }

    public User create(User usuario) throws Exception {
        try{
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String novaSenha = encoder.encode(usuario.getPassword());
            usuario.setPassword(novaSenha);
            return userRepository.save(usuario);

        }catch (Exception ex){
            throw new Exception("Este usuario nao pode ser criado");
        }

    }


}