package org.example.application.service;

import lombok.RequiredArgsConstructor;
import org.example.api.dto.disciplina.DisciplinaRequestDTO;
import org.example.api.dto.disciplina.DisciplinaResponseDTO;
import org.example.domain.entity.Disciplina;
import org.example.repository.DisciplinaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;

    public DisciplinaResponseDTO cadastrarDisciplina(DisciplinaRequestDTO dto) {
        if (disciplinaRepository.existsByCodigo(dto.getCodigo())) {
            throw new IllegalArgumentException("Já existe uma disciplina com este código.");
        }

        Disciplina disciplina = Disciplina.builder()
                .nome(dto.getNome())
                .codigo(dto.getCodigo())
                .build();

        Disciplina salva = disciplinaRepository.save(disciplina);

        return DisciplinaResponseDTO.builder()
                .id(salva.getId())
                .nome(salva.getNome())
                .codigo(salva.getCodigo())
                .build();
    }

    public List<DisciplinaResponseDTO> listarTodos() {
        return disciplinaRepository.findAll()
                .stream()
                .map(d -> DisciplinaResponseDTO.builder()
                        .id(d.getId())
                        .nome(d.getNome())
                        .codigo(d.getCodigo())
                        .build())
                .toList();
    }
}
