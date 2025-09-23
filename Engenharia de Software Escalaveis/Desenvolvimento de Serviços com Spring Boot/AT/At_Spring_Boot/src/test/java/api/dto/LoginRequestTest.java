package api.dto;

import org.example.api.dto.auth.LoginRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
class LoginRequestTest {

    @Test
    void deveCriarLoginRequestDTOComSetters() {
        LoginRequestDTO request = new LoginRequestDTO();
        request.setEmail("user@email.com");
        request.setSenha("abcdef");

        assertEquals("user@email.com", request.getEmail());
        assertEquals("abcdef", request.getSenha());
    }
}
