package leticia.edu.login_backend.service;

import leticia.edu.login_backend.entity.User;
import leticia.edu.login_backend.enumerator.UserRole;
import leticia.edu.login_backend.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

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

        User result =userService.login(newUser);

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
        // Arrange
        User newUser = new User();
        newUser.setId(1L);  // Usuário com ID, o que deve causar falha
        newUser.setUsername("Leticia");
        newUser.setEmail("teste@gmail");
        newUser.setPassword("123456789");
        newUser.setUserRole(UserRole.ADMIN);

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> userService.login(newUser));
        assertEquals("Este usuario ja existe", exception.getMessage());
        // Verify
        verify(userRepository, never()).save(newUser);  // Certifique-se de que o repositório nunca foi chamado
    }
}