package org.example.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.api.dto.matricula.MatriculaRequestDTO;
import org.example.api.dto.matricula.MatriculaResponseDTO;
import org.example.api.dto.matricula.NotaRequestDTO;
import org.example.application.service.MatriculaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matriculas")
@RequiredArgsConstructor
public class MatriculaController {

    private final MatriculaService matriculaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MatriculaResponseDTO matricular(@Valid @RequestBody MatriculaRequestDTO dto) {
        return matriculaService.matricularAluno(dto);
    }

    @PatchMapping("/{id}/nota")
    public MatriculaResponseDTO atribuirNota(@PathVariable Long id,
                                             @Valid @RequestBody NotaRequestDTO dto) {
        return matriculaService.atribuirNota(id, dto.getNota());
    }

    @GetMapping("/disciplina/{disciplinaId}/aprovados")
    public List<MatriculaResponseDTO> listarAprovados(@PathVariable Long disciplinaId) {
        return matriculaService.listarAprovadosPorDisciplina(disciplinaId);
    }

    @GetMapping("/disciplina/{disciplinaId}/reprovados")
    public List<MatriculaResponseDTO> listarReprovados(@PathVariable Long disciplinaId) {
        return matriculaService.listarReprovadosPorDisciplina(disciplinaId);
    }

    @GetMapping("/disciplina/{disciplinaId}")
    public List<MatriculaResponseDTO> listarAlunosPorDisciplina(@PathVariable Long disciplinaId) {
        return matriculaService.listarPorDisciplina(disciplinaId);
    }

    @GetMapping("/aluno/{alunoId}")
    public List<MatriculaResponseDTO> listarDisciplinasPorAluno(@PathVariable Long alunoId) {
        return matriculaService.listarPorAluno(alunoId);
    }

    @GetMapping
    public List<MatriculaResponseDTO> listarTodas() {
        return matriculaService.listarTodas();
    }
}
