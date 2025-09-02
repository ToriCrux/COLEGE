package org.example.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.domain.Departamento;
import org.example.domain.Funcionario;
import org.example.repository.DepartamentoRepository;
import org.example.repository.FuncionarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class FuncionarioControllerIT {

    @Autowired MockMvc mvc;
    @Autowired ObjectMapper om;
    @Autowired DepartamentoRepository depRepo;
    @Autowired FuncionarioRepository funcRepo;

    Long depId;

    @BeforeEach
    void setup() {
        funcRepo.deleteAll();
        depRepo.deleteAll();
        depId = depRepo.save(Departamento.builder().nome("Atendimento").build()).getId();
    }

    private Funcionario novo(String email) {
        return Funcionario.builder()
                .nome("Ana")
                .email(email)
                .cargo("Vendedora")
                .salario(new BigDecimal("3500.00"))
                .dataAdmissao(LocalDate.now())
                .departamento(depRepo.findById(depId).orElseThrow())
                .build();
    }

    @Test
    void fluxoCrud_Completo() throws Exception {
        String location = mvc.perform(post("/api/funcionarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(novo("ana@livraria.com"))))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getHeader("Location");

        mvc.perform(get(location))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("ana@livraria.com"));

        Funcionario upd = novo("ana@livraria.com");
        upd.setCargo("Supervisora");
        mvc.perform(put(location)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(upd)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cargo").value("Supervisora"));

        mvc.perform(get("/api/funcionarios")).andExpect(status().isOk());

        mvc.perform(delete(location)).andExpect(status().isNoContent());

        mvc.perform(get(location)).andExpect(status().isNotFound());
    }

    @Test
    void salarioNegativo_DeveRetornar400() throws Exception {
        Funcionario invalido = novo("x@x.com");
        invalido.setSalario(new BigDecimal("-1.00"));
        mvc.perform(post("/api/funcionarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(invalido)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fields.salario").exists());
    }

    @Test
    void emailDuplicado_DeveRetornar409() throws Exception {
        mvc.perform(post("/api/funcionarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(novo("dup@empresa.com"))))
                .andExpect(status().isCreated());

        mvc.perform(post("/api/funcionarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(novo("dup@empresa.com"))))
                .andExpect(status().isConflict());
    }

    @Test
    void metodoNaoPermitido_DeveRetornar405() throws Exception {
        mvc.perform(post("/api/funcionarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isMethodNotAllowed());
    }
}
