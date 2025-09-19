package org.example.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.api.dto.aluno.AlunoRequestDTO;
import org.example.api.dto.aluno.AlunoResponseDTO;
import org.example.application.service.AlunoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlunoResponseDTO cadastrar(@Valid @RequestBody AlunoRequestDTO dto) {
        return alunoService.cadastrarAluno(dto);
    }

    @GetMapping
    public List<AlunoResponseDTO> listarTodos() {
        return alunoService.listarTodos();
    }
}
