package org.example.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.domain.Departamento;
import org.example.domain.Produto;
import org.example.repository.DepartamentoRepository;
import org.example.repository.ProdutoRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProdutoControllerIT {

    @Autowired MockMvc mvc;
    @Autowired ObjectMapper om;
    @Autowired DepartamentoRepository depRepo;
    @Autowired ProdutoRepository prodRepo;

    Long depId;

    @BeforeEach
    void setup(){
        prodRepo.deleteAll();
        depRepo.deleteAll();
        depId = depRepo.save(Departamento.builder().nome("Tecnologia").build()).getId();
    }

    Produto novo(String isbn){
        return Produto.builder()
                .titulo("Spring Boot na Prática")
                .autor("João Silva")
                .isbn(isbn)
                .editora("Editora Exemplo")
                .anoPublicacao(2024)
                .preco(new BigDecimal("99.90"))
                .estoque(10)
                .departamento(depRepo.findById(depId).orElseThrow())
                .build();
    }

    @Test
    void fluxoCrud_Completo() throws Exception {
        String location = mvc.perform(post("/api/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(novo("1234567890"))))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andReturn().getResponse().getHeader("Location");

        mvc.perform(get(location))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Spring Boot na Prática"))
                .andExpect(jsonPath("$.isbn").value("1234567890"));

        Produto atualizado = novo("1234567890");
        atualizado.setTitulo("Spring Boot Avançado");
        atualizado.setAnoPublicacao(2025);
        mvc.perform(put(location)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(atualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Spring Boot Avançado"))
                .andExpect(jsonPath("$.anoPublicacao").value(2025));

        mvc.perform(get("/api/produtos"))
                .andExpect(status().isOk());

        mvc.perform(delete(location)).andExpect(status().isNoContent());

        mvc.perform(get(location)).andExpect(status().isNotFound());
    }

    @Test
    void postInvalido_DeveRetornar400() throws Exception {
        Produto invalido = novo("1111111111");
        invalido.setTitulo(""); // viola @NotBlank
        mvc.perform(post("/api/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(invalido)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Validation error"))
                .andExpect(jsonPath("$.fields.titulo").exists());
    }

    @Test
    void metodoNaoPermitido_DeveRetornar405() throws Exception {
        mvc.perform(post("/api/produtos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    void isbnDuplicado_DeveRetornar409() throws Exception {
        mvc.perform(post("/api/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(novo("DUPL-001"))))
                .andExpect(status().isCreated());

        mvc.perform(post("/api/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(novo("DUPL-001"))))
                .andExpect(status().isConflict());
    }
}
