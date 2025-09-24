package domain.entity;

import org.example.domain.entity.Matricula;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
class MatriculaTest {

    @Test
    void deveCriarMatriculaEValidarGettersSetters() {
        Matricula m = new Matricula();
        m.setId("1");
        m.setNota(9.5);

        assertEquals("1", m.getId());
        assertEquals(9.5, m.getNota());
        assertNotNull(m.toString());
    }
}
