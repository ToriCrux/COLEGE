package org.example.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.api.dto.matricula.MatriculaRequestDTO;
import org.example.api.dto.matricula.MatriculaResponseDTO;
import org.example.api.dto.matricula.NotaRequestDTO;
import org.example.application.service.MatriculaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> atribuirNota(@PathVariable String id,
                                          @Valid @RequestBody NotaRequestDTO dto) {
        if (dto == null || dto.getNota() == null) {
            return ResponseEntity.badRequest().body("O campo 'nota' é obrigatório");
        }

        try {
            MatriculaResponseDTO response = matriculaService.atribuirNota(id, dto.getNota());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao atualizar nota: " + e.getMessage());
        }
    }

    @GetMapping("/aprovados")
    public List<MatriculaResponseDTO> listarAprovados() {
        return matriculaService.listarAprovados();
    }

    @GetMapping("/reprovados")
    public List<MatriculaResponseDTO> listarReprovados() {
        return matriculaService.listarReprovados();
    }

    @GetMapping("/disciplina/{disciplinaId}/aprovados")
    public List<MatriculaResponseDTO> listarAprovados(@PathVariable String disciplinaId) {
        return matriculaService.listarAprovadosPorDisciplina(disciplinaId);
    }

    @GetMapping("/disciplina/{disciplinaId}/reprovados")
    public List<MatriculaResponseDTO> listarReprovados(@PathVariable String disciplinaId) {
        return matriculaService.listarReprovadosPorDisciplina(disciplinaId);
    }

    @GetMapping("/disciplina/{disciplinaId}")
    public List<MatriculaResponseDTO> listarAlunosPorDisciplina(@PathVariable String disciplinaId) {
        return matriculaService.listarPorDisciplina(disciplinaId);
    }

    @GetMapping("/aluno/{alunoId}")
    public List<MatriculaResponseDTO> listarDisciplinasPorAluno(@PathVariable String alunoId) {
        return matriculaService.listarPorAluno(alunoId);
    }

    @GetMapping
    public List<MatriculaResponseDTO> listarTodas() {
        return matriculaService.listarTodas();
    }
}
