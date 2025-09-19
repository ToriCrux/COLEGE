package api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.AcademicoApiApplication;
import org.example.domain.entity.Disciplina;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = AcademicoApiApplication.class)
@AutoConfigureMockMvc(addFilters = false)
class DisciplinaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void deveCadastrarDisciplina() throws Exception {
        Disciplina disciplina = new Disciplina();
        disciplina.setNome("Inglês");
        disciplina.setCodigo("ING404");

        mockMvc.perform(post("/api/disciplinas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(disciplina)))
                .andExpect(status().isCreated());
    }
}
