package application.service;

import org.example.api.dto.professor.ProfessorResponseDTO;
import org.example.application.service.ProfessorService;
import org.example.domain.entity.Professor;
import org.example.repository.ProfessorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProfessorServiceTest {

    @Mock
    private ProfessorRepository professorRepository;

    @InjectMocks
    private ProfessorService professorService;

    private Professor professor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        professor = new Professor("1", "Professor Teste", "professor@email.com", "123456");
    }

    @Test
    void deveListarProfessores() {
        when(professorRepository.findAll()).thenReturn(List.of(professor));

        List<ProfessorResponseDTO> resultado = professorService.listarTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("professor@email.com", resultado.get(0).getEmail());
    }
}
