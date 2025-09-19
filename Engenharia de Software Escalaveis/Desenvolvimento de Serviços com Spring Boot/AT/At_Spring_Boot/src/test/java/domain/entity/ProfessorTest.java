package domain.entity;

import org.example.domain.entity.Professor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfessorTest {

    @Test
    void deveCriarProfessorEValidarGettersSetters() {
        Professor p = new Professor();
        p.setId(1L);
        p.setNome("Carlos");
        p.setEmail("carlos@email.com");
        p.setSenha("123456");

        assertEquals(1L, p.getId());
        assertEquals("Carlos", p.getNome());
        assertEquals("carlos@email.com", p.getEmail());
        assertEquals("123456", p.getSenha());
        assertNotNull(p.toString());
    }

}

