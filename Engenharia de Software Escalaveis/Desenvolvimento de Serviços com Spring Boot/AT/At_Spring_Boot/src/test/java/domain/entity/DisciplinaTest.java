package domain.entity;

import org.example.domain.entity.Disciplina;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
class DisciplinaTest {

    @Test
    void deveCriarDisciplinaEValidarGettersSetters() {
        Disciplina d = new Disciplina();
        d.setId(1L);
        d.setNome("Matemática");
        d.setCodigo("MAT01");

        assertEquals(1L, d.getId());
        assertEquals("Matemática", d.getNome());
        assertEquals("MAT01", d.getCodigo());
        assertNotNull(d.toString());
    }

}
