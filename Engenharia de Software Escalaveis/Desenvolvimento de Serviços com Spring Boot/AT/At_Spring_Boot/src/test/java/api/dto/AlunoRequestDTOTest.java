package api.dto;

import org.example.api.dto.aluno.AlunoRequestDTO;
import org.example.domain.valueobject.Endereco;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlunoRequestDTOTest {

    @Test
    void deveCriarAlunoRequestDTOComSetters() {
        AlunoRequestDTO dto = new AlunoRequestDTO();
        dto.setNome("João");
        dto.setEmail("joao@email.com");
        dto.setCpf("12345678900");
        dto.setTelefone("999999999");
        dto.setEndereco(new Endereco("Rua A", "100", "Centro", "CidadeX", "SP", "12345000"));

        assertEquals("João", dto.getNome());
        assertEquals("joao@email.com", dto.getEmail());
        assertEquals("12345678900", dto.getCpf());
        assertEquals("999999999", dto.getTelefone());
        assertNotNull(dto.getEndereco());
    }
}
