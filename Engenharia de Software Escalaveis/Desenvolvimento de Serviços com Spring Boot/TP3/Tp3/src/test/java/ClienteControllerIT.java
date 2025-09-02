package org.example.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.domain.Cliente;
import org.example.repository.ClienteRepository;
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
class ClienteControllerIT {

    @Autowired MockMvc mvc;
    @Autowired ObjectMapper om;
    @Autowired ClienteRepository repo;

    @BeforeEach
    void setup() {
        repo.deleteAll();
    }

    private Cliente novo(String email, String doc) {
        return Cliente.builder()
                .nome("Maria")
                .email(email)
                .documento(doc)
                .telefone("11999999999")
                .build();
    }

    @Test
    void fluxoCrud_Completo() throws Exception {
        String location = mvc.perform(post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(novo("maria@ex.com","12345678900"))))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getHeader("Location");

        mvc.perform(get(location))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("maria@ex.com"));

        Cliente upd = novo("maria@ex.com","12345678900");
        upd.setNome("Maria Silva");
        mvc.perform(put(location)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(upd)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Maria Silva"));

        mvc.perform(get("/api/clientes")).andExpect(status().isOk());

        mvc.perform(delete(location)).andExpect(status().isNoContent());

        mvc.perform(get(location)).andExpect(status().isNotFound());
    }

    @Test
    void emailInvalido_DeveRetornar400() throws Exception {
        Cliente invalido = novo("email-invalido","11111111111");
        mvc.perform(post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(invalido)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fields.email").exists());
    }

    @Test
    void documentoDuplicado_DeveRetornar409() throws Exception {
        mvc.perform(post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(novo("a@a.com","DOC-001"))))
                .andExpect(status().isCreated());

        mvc.perform(post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(novo("b@b.com","DOC-001"))))
                .andExpect(status().isConflict());
    }

    @Test
    void metodoNaoPermitido_DeveRetornar405() throws Exception {
        mvc.perform(post("/api/clientes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isMethodNotAllowed());
    }
}
