package api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.example.AcademicoApiApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = AcademicoApiApplication.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
@Transactional
class MatriculaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void deveMatricularNovoAlunoEmDisciplina() throws Exception {
        String cpfUnico = UUID.randomUUID().toString().substring(0, 11).replace("-", "1");

        String alunoJson = """
        {
          "nome": "Aluno Teste",
          "cpf": "%s",
          "email": "aluno@teste.com",
          "telefone": "11999998888",
          "endereco": {
            "rua": "Rua X",
            "numero": "123",
            "bairro": "Centro",
            "cidade": "São Paulo",
            "estado": "SP",
            "cep": "01001000"
          }
        }
        """.formatted(cpfUnico);

        String response = mockMvc.perform(post("/api/alunos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(alunoJson))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Long alunoId = mapper.readTree(response).get("id").asLong();

        String matriculaJson = """
            {
              "alunoId": %d,
              "disciplinaId": 1
            }
            """.formatted(alunoId);

        mockMvc.perform(post("/api/matriculas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(matriculaJson))
                .andExpect(status().isCreated());
    }
}
