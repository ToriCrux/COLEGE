package api.dto;

import org.example.api.dto.disciplina.DisciplinaResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
class DisciplinaResponseDTOTest {

    @Test
    void deveCriarDisciplinaResponseDTOComSetters() {
        DisciplinaResponseDTO dto = new DisciplinaResponseDTO();
        dto.setId(1L);
        dto.setNome("História");
        dto.setCodigo("HIS202");

        assertEquals(1L, dto.getId());
        assertEquals("História", dto.getNome());
        assertEquals("HIS202", dto.getCodigo());
    }
}

