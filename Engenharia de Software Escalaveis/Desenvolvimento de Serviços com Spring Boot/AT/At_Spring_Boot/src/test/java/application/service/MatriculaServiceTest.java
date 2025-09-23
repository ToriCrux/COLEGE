package application.service;

import org.example.api.dto.matricula.MatriculaRequestDTO;
import org.example.api.dto.matricula.MatriculaResponseDTO;
import org.example.application.service.MatriculaService;
import org.example.domain.entity.Aluno;
import org.example.domain.entity.Disciplina;
import org.example.domain.entity.Matricula;
import org.example.repository.AlunoRepository;
import org.example.repository.DisciplinaRepository;
import org.example.repository.MatriculaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
class MatriculaServiceTest {

    @Mock
    private MatriculaRepository matriculaRepository;
    @Mock
    private AlunoRepository alunoRepository;
    @Mock
    private DisciplinaRepository disciplinaRepository;

    @InjectMocks
    private MatriculaService matriculaService;

    private Aluno aluno;
    private Disciplina disciplina;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        aluno = Aluno.builder().id(1L).nome("Maria").build();
        disciplina = Disciplina.builder().id(1L).nome("Matemática").build();
    }

    @Test
    void deveMatricularAlunoComSucesso() {
        MatriculaRequestDTO dto = MatriculaRequestDTO.builder()
                .alunoId(1L)
                .disciplinaId(1L)
                .build();

        when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));
        when(disciplinaRepository.findById(1L)).thenReturn(Optional.of(disciplina));
        when(matriculaRepository.findByDisciplinaId(1L)).thenReturn(List.of());

        Matricula salva = Matricula.builder()
                .id(10L)
                .aluno(aluno)
                .disciplina(disciplina)
                .build();

        when(matriculaRepository.save(any(Matricula.class))).thenReturn(salva);

        MatriculaResponseDTO response = matriculaService.matricularAluno(dto);

        assertNotNull(response);
        assertEquals("Maria", response.getAlunoNome());
        assertEquals("Matemática", response.getDisciplinaNome());
        verify(matriculaRepository).save(any(Matricula.class));
    }

    @Test
    void deveAtribuirNota() {
        Matricula matricula = Matricula.builder()
                .id(10L)
                .aluno(aluno)
                .disciplina(disciplina)
                .build();

        when(matriculaRepository.findById(10L)).thenReturn(Optional.of(matricula));
        when(matriculaRepository.save(any(Matricula.class))).thenReturn(matricula);

        MatriculaResponseDTO response = matriculaService.atribuirNota(10L, 8.5);

        assertNotNull(response);
        verify(matriculaRepository).save(matricula);
        assertEquals("Maria", response.getAlunoNome());
    }

    @Test
    void deveListarAprovadosPorDisciplina() {
        Matricula m = Matricula.builder()
                .id(1L)
                .aluno(aluno)
                .disciplina(disciplina)
                .nota(9.0)
                .build();

        when(matriculaRepository.findByDisciplinaIdAndNotaGreaterThanEqual(1L, 7.0))
                .thenReturn(List.of(m));

        var lista = matriculaService.listarAprovadosPorDisciplina(1L);

        assertEquals(1, lista.size());
        assertEquals("Maria", lista.get(0).getAlunoNome());
    }

    @Test
    void deveListarReprovadosPorDisciplina() {
        Matricula m = Matricula.builder()
                .id(1L)
                .aluno(aluno)
                .disciplina(disciplina)
                .nota(5.0)
                .build();

        when(matriculaRepository.findByDisciplinaIdAndNotaLessThan(1L, 7.0))
                .thenReturn(List.of(m));

        var lista = matriculaService.listarReprovadosPorDisciplina(1L);

        assertEquals(1, lista.size());
        assertEquals("Maria", lista.get(0).getAlunoNome());
    }
}
