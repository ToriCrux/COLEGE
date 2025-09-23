package org.example.application.service;

import lombok.RequiredArgsConstructor;
import org.example.api.dto.professor.ProfessorResponseDTO;
import org.example.domain.entity.Professor;
import org.example.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public List<ProfessorResponseDTO> listarTodos() {
        return professorRepository.findAll()
                .stream()
                .map(p -> ProfessorResponseDTO.builder()
                        .id(p.getId())
                        .nome(p.getNome())
                        .email(p.getEmail())
                        .build())
                .toList();
    }
}
