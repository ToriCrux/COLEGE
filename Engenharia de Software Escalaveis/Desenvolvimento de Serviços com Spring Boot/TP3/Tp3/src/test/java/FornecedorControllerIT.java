package org.example.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.domain.Fornecedor;
import org.example.repository.FornecedorRepository;
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
class FornecedorControllerIT {

    @Autowired MockMvc mvc;
    @Autowired ObjectMapper om;
    @Autowired FornecedorRepository repo;

    @BeforeEach
    void setup() {
        repo.deleteAll();
    }

    private Fornecedor novo(String cnpj, String email) {
        return Fornecedor.builder()
                .nome("Distribuidora Central")
                .cnpj(cnpj)
                .email(email)
                .telefone("1133334444")
                .build();
    }

    @Test
    void fluxoCrud_Completo() throws Exception {
        String location = mvc.perform(post("/api/fornecedores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(novo("12.345.678/0001-00","contato@dist.com"))))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getHeader("Location");

        mvc.perform(get(location))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Distribuidora Central"));

        Fornecedor upd = novo("12.345.678/0001-00","contato@dist.com");
        upd.setTelefone("1144445555");
        mvc.perform(put(location)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(upd)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.telefone").value("1144445555"));

        mvc.perform(get("/api/fornecedores")).andExpect(status().isOk());

        mvc.perform(delete(location)).andExpect(status().isNoContent());

        mvc.perform(get(location)).andExpect(status().isNotFound());
    }

    @Test
    void emailInvalido_DeveRetornar400() throws Exception {
        Fornecedor invalido = novo("00.000.000/0000-00","email-invalido");
        mvc.perform(post("/api/fornecedores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(invalido)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fields.email").exists());
    }

    @Test
    void cnpjDuplicado_DeveRetornar409() throws Exception {
        mvc.perform(post("/api/fornecedores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(novo("CNPJ-001","a@a.com"))))
                .andExpect(status().isCreated());

        mvc.perform(post("/api/fornecedores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(novo("CNPJ-001","b@b.com"))))
                .andExpect(status().isConflict());
    }

    @Test
    void metodoNaoPermitido_DeveRetornar405() throws Exception {
        mvc.perform(post("/api/fornecedores/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isMethodNotAllowed());
    }
}
