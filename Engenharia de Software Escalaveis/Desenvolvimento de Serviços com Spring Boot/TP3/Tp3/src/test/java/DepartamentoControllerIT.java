package org.example.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.domain.Departamento;
import org.example.repository.DepartamentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class DepartamentoControllerIT {

    @Autowired MockMvc mvc;
    @Autowired ObjectMapper om;
    @Autowired DepartamentoRepository repo;

    @BeforeEach
    void setup() {
        repo.deleteAll();
    }

    private Departamento novo(String nome) {
        return Departamento.builder().nome(nome).build();
    }

    @Test
    void fluxoCrud_Completo() throws Exception {
        String location = mvc.perform(post("/api/departamentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(novo("Tecnologia"))))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getHeader("Location");

        mvc.perform(get(location))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Tecnologia"));

        mvc.perform(put(location)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(novo("Ficção"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Ficção"));

        mvc.perform(get("/api/departamentos")).andExpect(status().isOk());

        mvc.perform(delete(location)).andExpect(status().isNoContent());

        mvc.perform(get(location)).andExpect(status().isNotFound());
    }

    @Test
    void nomeVazio_DeveRetornar400() throws Exception {
        mvc.perform(post("/api/departamentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(novo(""))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Validation error"))
                .andExpect(jsonPath("$.fields.nome").exists());
    }

    @Test
    void nomeDuplicado_DeveRetornar409() throws Exception {
        mvc.perform(post("/api/departamentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(novo("Tecnologia"))))
                .andExpect(status().isCreated());

        mvc.perform(post("/api/departamentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(novo("Tecnologia"))))
                .andExpect(status().isConflict());
    }

    @Test
    void metodoNaoPermitido_DeveRetornar405() throws Exception {
        mvc.perform(post("/api/departamentos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isMethodNotAllowed());
    }
}
