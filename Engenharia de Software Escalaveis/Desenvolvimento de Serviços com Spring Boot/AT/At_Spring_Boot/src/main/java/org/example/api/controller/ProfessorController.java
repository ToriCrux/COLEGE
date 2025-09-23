package org.example.api.controller;

import lombok.RequiredArgsConstructor;
import org.example.api.dto.professor.ProfessorResponseDTO;
import org.example.application.service.ProfessorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professores")
@RequiredArgsConstructor
public class ProfessorController {

    private final ProfessorService professorService;

    @GetMapping
    public List<ProfessorResponseDTO> listarTodos() {
        return professorService.listarTodos();
    }
}
