package domain.entity;

import org.example.domain.entity.Aluno;
import org.example.domain.valueobject.Endereco;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
class AlunoTest {

    @Test
    void deveCriarAlunoEValidarGettersSetters() {
        Endereco endereco = new Endereco("Rua A", "100", "Centro", "CidadeX", "SP", "12345000");

        Aluno aluno = new Aluno();
        aluno.setId(1L);
        aluno.setNome("João");
        aluno.setEmail("joao@email.com");
        aluno.setCpf("12345678900");
        aluno.setTelefone("999999999");
        aluno.setEndereco(endereco);

        assertEquals(1L, aluno.getId());
        assertEquals("João", aluno.getNome());
        assertEquals("joao@email.com", aluno.getEmail());
        assertEquals("12345678900", aluno.getCpf());
        assertEquals("999999999", aluno.getTelefone());
        assertEquals(endereco, aluno.getEndereco());
        assertNotNull(aluno.toString());
    }
}

