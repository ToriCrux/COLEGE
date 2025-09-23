package application.service;

import org.example.api.dto.aluno.AlunoRequestDTO;
import org.example.application.service.AlunoService;
import org.example.domain.entity.Aluno;
import org.example.domain.valueobject.Endereco;
import org.example.repository.AlunoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
class AlunoServiceTest {

    @Mock
    private AlunoRepository alunoRepository;

    @InjectMocks
    private AlunoService alunoService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCadastrarAlunoComSucesso() {
        AlunoRequestDTO dto = AlunoRequestDTO.builder()
                .nome("Maria")
                .cpf("12345678900")
                .email("maria@email.com")
                .telefone("99999-9999")
                .endereco(new Endereco("Rua A","100","Centro","Cidade","Estado","12345-000"))
                .build();

        when(alunoRepository.existsByCpf(dto.getCpf())).thenReturn(false);

        Aluno salvo = Aluno.builder()
                .id(1L)
                .nome(dto.getNome())
                .cpf(dto.getCpf())
                .email(dto.getEmail())
                .telefone(dto.getTelefone())
                .endereco(dto.getEndereco())
                .build();

        when(alunoRepository.save(any(Aluno.class))).thenReturn(salvo);

        var response = alunoService.cadastrarAluno(dto);

        assertNotNull(response);
        assertEquals("Maria", response.getNome());
        verify(alunoRepository).save(any(Aluno.class));
    }

    @Test
    void deveListarTodosOsAlunos() {
        Aluno aluno = Aluno.builder()
                .id(1L)
                .nome("João")
                .cpf("98765432100")
                .email("joao@email.com")
                .telefone("88888-8888")
                .endereco(new Endereco("Rua B","200","Bairro","Cidade","Estado","99999-000"))
                .build();

        when(alunoRepository.findAll()).thenReturn(List.of(aluno));

        var lista = alunoService.listarTodos();

        assertEquals(1, lista.size());
        assertEquals("João", lista.get(0).getNome());
        verify(alunoRepository).findAll();
    }
}
