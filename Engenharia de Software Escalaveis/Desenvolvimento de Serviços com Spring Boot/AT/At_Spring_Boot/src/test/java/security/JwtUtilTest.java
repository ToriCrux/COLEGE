package security;

import org.example.security.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
    }

    @Test
    void deveGerarETestarTokenValido() {
        String email = "teste@email.com";
        String token = jwtUtil.gerarToken(email);

        assertNotNull(token);
        String subject = jwtUtil.validarToken(token);

        assertEquals(email, subject);
    }

    @Test
    void deveRetornarExceptionParaTokenInvalido() {
        String tokenInvalido = "token.falso";

        assertThrows(Exception.class, () -> {
            jwtUtil.validarToken(tokenInvalido);
        });
    }

    @Test
    void deveGerarTokensDiferentesParaUsuariosDiferentes() {
        String token1 = jwtUtil.gerarToken("usuario1@email.com");
        String token2 = jwtUtil.gerarToken("usuario2@email.com");

        assertNotEquals(token1, token2);
    }
}

