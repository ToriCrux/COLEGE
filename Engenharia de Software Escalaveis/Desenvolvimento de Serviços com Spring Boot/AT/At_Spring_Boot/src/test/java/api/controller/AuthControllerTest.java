package api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.AcademicoApiApplication;
import org.example.domain.entity.Professor;
import org.example.repository.ProfessorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = AcademicoApiApplication.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private ProfessorRepository professorRepository;

    @Test
    void deveLogarProfessorComCredenciaisValidas() throws Exception {
        professorRepository.save(new Professor(
                null,
                "Professor Teste",
                "professor@email.com",
                "123456"
        ));

        String json = """
            {
              "email": "professor@email.com",
              "senha": "123456"
            }
            """;

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }
}
