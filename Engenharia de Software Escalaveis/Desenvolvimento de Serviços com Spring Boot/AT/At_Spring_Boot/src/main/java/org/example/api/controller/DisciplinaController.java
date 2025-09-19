package org.example.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.api.dto.disciplina.DisciplinaRequestDTO;
import org.example.api.dto.disciplina.DisciplinaResponseDTO;
import org.example.application.service.DisciplinaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplinas")
@RequiredArgsConstructor
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DisciplinaResponseDTO cadastrar(@Valid @RequestBody DisciplinaRequestDTO dto) {
        return disciplinaService.cadastrarDisciplina(dto);
    }

    @GetMapping
    public List<DisciplinaResponseDTO> listarTodos() {
        return disciplinaService.listarTodos();
    }
}
