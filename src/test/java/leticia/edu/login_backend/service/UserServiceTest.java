package leticia.edu.login_backend.service;

import leticia.edu.login_backend.entity.User;
import leticia.edu.login_backend.enumerator.UserRole;
import leticia.edu.login_backend.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;


    @Test
    @DisplayName(value = " shouldReturnLoginWithSuccess")
    void shouldReturnLoginWithSuccess() throws Exception {
        User newUser = new User();
        newUser.setUsername("Leticia");
        newUser.setEmail("teste@gmail");
        newUser.setPassword("123456789");
        newUser.setUserRole(UserRole.ADMIN);

        when(userRepository.save(newUser)).thenReturn(newUser);

        User result = userService.login(newUser);

        verify(userRepository).save(newUser);
        assertEquals(result, newUser);
    }

    @Test
    @DisplayName("shouldThrowExceptionWhenUserIdIsNotNull")
    void shouldThrowExceptionWhenUserIdIsNotNull() {
        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setUsername("Leticia");
        existingUser.setEmail("teste@gmail.com");
        existingUser.setPassword("123456789");
        existingUser.setUserRole(UserRole.ADMIN);

        Exception exception = assertThrows(Exception.class, () -> userService.login(existingUser));
        assertEquals("Este usuario ja existe", exception.getMessage());
    }

    @Test
    @DisplayName("ShouldReturnLoginWithFailed")
    void shouldReturnLoginWithFailed() {
        User newUser = new User();
        newUser.setId(1L);
        newUser.setUsername("Leticia");
        newUser.setEmail("teste@gmail");
        newUser.setPassword("123456789");
        newUser.setUserRole(UserRole.ADMIN);

        Exception exception = assertThrows(Exception.class, () -> userService.login(newUser));
        assertEquals("Este usuario ja existe", exception.getMessage());

        verify(userRepository, never()).save(newUser);
    }

    @Test
    @DisplayName("shouldReturnIdUser")
    public void shouldReturnIdUser() {

        User user = new User();
        user.setId(1L);
        Optional<User> existingUser = Optional.of(user);
        when(userRepository.findById(1L)).thenReturn(existingUser);
        Optional<User> foundUser = userService.findById(1L);
        assertEquals(foundUser.isPresent(),foundUser.get().getId().equals(1L));
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("shouldNotReturnIdUser")
    public void shouldNotReturnIdUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<User> foundUser = userService.findById(1L);
        assertFalse(foundUser.isPresent());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("shouldCreateUserWithSuccess")
    public void shouldCreateUserWithSuccess() throws Exception {

        User newUser = new User(1L, UserRole.USER, "leticia@gmail.com", "123456789", "leticia");
        User savedUser = new User(1L, UserRole.USER, "leticia@gmail.com", "encodedPassword", "leticia");

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        User result = userService.create(newUser);

        assertEquals(savedUser.getId(), result.getId());
        assertEquals(savedUser.getEmail(), result.getEmail());
        assertEquals(savedUser.getUsername(), result.getUsername());
        assertEquals(savedUser.getUserRole(UserRole.ADMIN), result.getUserRole(UserRole.ADMIN));
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    @DisplayName("shouldCreateUserWithFailed")
    public void shouldCreateUserWithFailed() throws Exception {
        User savedUser = new User();
        when(userRepository.save(any(User.class))).thenThrow(new RuntimeException("Falha ao salvar usuário"));
        Exception exception = assertThrows(Exception.class, () -> userService.create(savedUser));
        assertEquals("Este usuario nao pode ser criado", exception.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }

}