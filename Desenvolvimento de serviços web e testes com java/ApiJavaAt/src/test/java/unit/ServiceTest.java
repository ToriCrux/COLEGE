package unit;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.example.dto.UsuarioDTOInput;
import org.example.dto.UsuarioDTOOutput;
import org.example.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ServiceTest {
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        usuarioService = new UsuarioService();
    }

    @Test
    void testInserirUsuario() {
        UsuarioDTOInput usuarioDTO = criarUsuarioDTO(1, "Victoria Cruz", "senha");

        usuarioService.inserir(usuarioDTO);

        List<UsuarioDTOOutput> usuarios = usuarioService.listar();
        assertEquals(1, usuarios.size());

        System.out.println("Parabéns!! Teste de inserção de usuário bem-sucedido!");
    }
    private UsuarioDTOInput criarUsuarioDTO(int id, String nome, String senha) {
        UsuarioDTOInput usuarioDTO = new UsuarioDTOInput();
        usuarioDTO.setId(id);
        usuarioDTO.setNome(nome);
        usuarioDTO.setSenha(senha);
        return usuarioDTO;
    }
}
