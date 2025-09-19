package domain.entity;

import org.example.domain.entity.Matricula;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatriculaTest {

    @Test
    void deveCriarMatriculaEValidarGettersSetters() {
        Matricula m = new Matricula();
        m.setId(1L);
        m.setNota(9.5);

        assertEquals(1L, m.getId());
        assertEquals(9.5, m.getNota());
        assertNotNull(m.toString());
    }
}

