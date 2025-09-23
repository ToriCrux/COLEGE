package api.dto;

import org.example.api.dto.matricula.NotaRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
class NotaRequestDTOTest {

    @Test
    void deveCriarNotaRequestDTOComSetters() {
        NotaRequestDTO dto = new NotaRequestDTO();
        dto.setNota(9.5);

        assertEquals(9.5, dto.getNota());
    }
}

