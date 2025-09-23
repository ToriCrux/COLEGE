package org.example.application.service;

import lombok.RequiredArgsConstructor;
import org.example.api.dto.disciplina.DisciplinaRequestDTO;
import org.example.api.dto.disciplina.DisciplinaResponseDTO;
import org.example.domain.entity.Disciplina;
import org.example.domain.entity.Professor;
import org.example.repository.DisciplinaRepository;
import org.example.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;
    private final ProfessorRepository professorRepository;

    public DisciplinaResponseDTO cadastrarDisciplina(DisciplinaRequestDTO dto) {
        if (disciplinaRepository.existsByCodigo(dto.getCodigo())) {
            throw new IllegalArgumentException("Já existe uma disciplina com este código.");
        }

        Professor professor = null;
        if (dto.getProfessor_id() != null) {
            professor = professorRepository.findById(dto.getProfessor_id())
                    .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));
        }

        Disciplina disciplina = Disciplina.builder()
                .nome(dto.getNome())
                .codigo(dto.getCodigo())
                .professor(professor)
                .build();

        Disciplina salva = disciplinaRepository.save(disciplina);

        return DisciplinaResponseDTO.builder()
                .id(salva.getId())
                .nome(salva.getNome())
                .codigo(salva.getCodigo())
                .professor_id(salva.getProfessor() != null ? salva.getProfessor().getId() : null)
                .build();
    }

    public List<DisciplinaResponseDTO> listarTodos() {
        return disciplinaRepository.findAll()
                .stream()
                .map(d -> DisciplinaResponseDTO.builder()
                        .id(d.getId())
                        .nome(d.getNome())
                        .codigo(d.getCodigo())
                        .professor_id(d.getProfessor() != null ? d.getProfessor().getId() : null)
                        .build())
                .toList();
    }

    public DisciplinaResponseDTO atualizar(Long id, DisciplinaRequestDTO dto) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));

        disciplina.setNome(dto.getNome());
        disciplina.setCodigo(dto.getCodigo());

        if (dto.getProfessor_id() != null) {
            Professor professor = professorRepository.findById(dto.getProfessor_id())
                    .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));
            disciplina.setProfessor(professor);
        }

        Disciplina salva = disciplinaRepository.save(disciplina);

        return DisciplinaResponseDTO.builder()
                .id(salva.getId())
                .nome(salva.getNome())
                .codigo(salva.getCodigo())
                .professor_id(salva.getProfessor() != null ? salva.getProfessor().getId() : null)
                .build();
    }
}
