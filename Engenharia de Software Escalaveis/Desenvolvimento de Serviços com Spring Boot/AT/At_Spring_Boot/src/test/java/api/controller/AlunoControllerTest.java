package api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.AcademicoApiApplication;
import org.example.domain.entity.Aluno;
import org.example.domain.valueobject.Endereco;
import org.example.repository.AlunoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = org.example.AcademicoApiApplication.class)
@AutoConfigureMockMvc(addFilters = false)
class AlunoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AlunoRepository alunoRepository;

    @Test
    void deveCadastrarAluno() throws Exception {
        String json = """
                {
                  "nome": "Maria Teste",
                  "cpf": "12345678900",
                  "email": "maria@test.com",
                  "telefone": "11999999999",
                  "endereco": {
                    "rua": "Rua Teste",
                    "numero": "100",
                    "bairro": "Centro",
                    "cidade": "São Paulo",
                    "estado": "SP",
                    "cep": "12345000"
                  }
                }
                """;

        mockMvc.perform(post("/api/alunos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
    }


}
