package leticia.edu.login_backend.repository;

import leticia.edu.login_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail (String email);

}
