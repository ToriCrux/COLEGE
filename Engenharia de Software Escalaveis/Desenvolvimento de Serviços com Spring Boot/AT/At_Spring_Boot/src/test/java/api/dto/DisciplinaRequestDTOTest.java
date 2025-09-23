package api.dto;

import org.example.api.dto.disciplina.DisciplinaRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
class DisciplinaRequestDTOTest {

    @Test
    void deveCriarDisciplinaRequestDTOComSetters() {
        DisciplinaRequestDTO dto = new DisciplinaRequestDTO();
        dto.setNome("Matemática");
        dto.setCodigo("MAT101");

        assertEquals("Matemática", dto.getNome());
        assertEquals("MAT101", dto.getCodigo());
    }
}
