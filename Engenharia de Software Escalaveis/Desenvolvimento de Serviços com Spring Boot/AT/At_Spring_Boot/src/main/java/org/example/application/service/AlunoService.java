package org.example.application.service;

import lombok.RequiredArgsConstructor;
import org.example.api.dto.aluno.AlunoRequestDTO;
import org.example.api.dto.aluno.AlunoResponseDTO;
import org.example.domain.entity.Aluno;
import org.example.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoResponseDTO cadastrarAluno(AlunoRequestDTO dto) {
        if (alunoRepository.existsByCpf(dto.getCpf())) {
            throw new IllegalArgumentException("Já existe um aluno com este CPF.");
        }

        Aluno aluno = Aluno.builder()
                .nome(dto.getNome())
                .cpf(dto.getCpf())
                .email(dto.getEmail())
                .telefone(dto.getTelefone())
                .endereco(dto.getEndereco())
                .build();

        Aluno salvo = alunoRepository.save(aluno);

        return AlunoResponseDTO.builder()
                .id(salvo.getId())
                .nome(salvo.getNome())
                .cpf(salvo.getCpf())
                .email(salvo.getEmail())
                .telefone(salvo.getTelefone())
                .endereco(salvo.getEndereco())
                .build();
    }

    public List<AlunoResponseDTO> listarTodos() {
        return alunoRepository.findAll()
                .stream()
                .map(aluno -> AlunoResponseDTO.builder()
                        .id(aluno.getId())
                        .nome(aluno.getNome())
                        .cpf(aluno.getCpf())
                        .email(aluno.getEmail())
                        .telefone(aluno.getTelefone())
                        .endereco(aluno.getEndereco())
                        .build())
                .toList();
    }
}
