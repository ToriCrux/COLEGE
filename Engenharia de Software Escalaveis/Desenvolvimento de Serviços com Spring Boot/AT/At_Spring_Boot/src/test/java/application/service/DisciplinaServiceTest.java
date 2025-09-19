package org.example.application.service;

import org.example.api.dto.disciplina.DisciplinaRequestDTO;
import org.example.domain.entity.Disciplina;
import org.example.repository.DisciplinaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DisciplinaServiceTest {

    @Mock
    private DisciplinaRepository disciplinaRepository;

    @InjectMocks
    private DisciplinaService disciplinaService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCadastrarDisciplinaComSucesso() {
        DisciplinaRequestDTO dto = DisciplinaRequestDTO.builder()
                .nome("Matemática")
                .codigo("MAT01")
                .build();

        when(disciplinaRepository.existsByCodigo(dto.getCodigo())).thenReturn(false);

        Disciplina salva = Disciplina.builder()
                .id(1L)
                .nome(dto.getNome())
                .codigo(dto.getCodigo())
                .build();

        when(disciplinaRepository.save(any(Disciplina.class))).thenReturn(salva);

        var response = disciplinaService.cadastrarDisciplina(dto);

        assertNotNull(response);
        assertEquals("Matemática", response.getNome());
        verify(disciplinaRepository).save(any(Disciplina.class));
    }

    @Test
    void deveListarTodasAsDisciplinas() {
        Disciplina disciplina = Disciplina.builder()
                .id(1L)
                .nome("Português")
                .codigo("PORT01")
                .build();

        when(disciplinaRepository.findAll()).thenReturn(List.of(disciplina));

        var lista = disciplinaService.listarTodos();

        assertEquals(1, lista.size());
        assertEquals("Português", lista.get(0).getNome());
        verify(disciplinaRepository).findAll();
    }
}
