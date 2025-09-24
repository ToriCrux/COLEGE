package org.example.application.service;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.example.api.dto.matricula.MatriculaRequestDTO;
import org.example.api.dto.matricula.MatriculaResponseDTO;
import org.example.domain.entity.Aluno;
import org.example.domain.entity.Disciplina;
import org.example.domain.entity.Matricula;
import org.example.repository.AlunoRepository;
import org.example.repository.DisciplinaRepository;
import org.example.repository.MatriculaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatriculaService {

    private final MatriculaRepository matriculaRepository;
    private final AlunoRepository alunoRepository;
    private final DisciplinaRepository disciplinaRepository;

    public MatriculaResponseDTO matricularAluno(MatriculaRequestDTO dto) {
        Aluno aluno = alunoRepository.findById(dto.getAlunoId())
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        Disciplina disciplina = disciplinaRepository.findById(dto.getDisciplinaId())
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));

        if (matriculaRepository.existsByAlunoIdAndDisciplinaId(aluno.getId(), disciplina.getId())) {
            throw new IllegalArgumentException("Este aluno já está matriculado nesta disciplina");
        }

        Matricula matricula = Matricula.builder()
                .aluno(aluno)
                .disciplina(disciplina)
                .nota(null)
                .build();

        Matricula salva = matriculaRepository.save(matricula);

        return toDTO(salva);
    }

    public MatriculaResponseDTO atribuirNota(String matriculaId, Double nota) {
        Matricula matricula = matriculaRepository.findById(matriculaId)
                .orElseGet(() -> matriculaRepository.findById(new ObjectId(matriculaId).toHexString())
                        .orElseThrow(() -> new IllegalArgumentException("Matrícula não encontrada")));

        matricula.setNota(nota);
        Matricula atualizada = matriculaRepository.save(matricula);

        return toDTO(atualizada);
    }

    public List<MatriculaResponseDTO> listarAprovadosPorDisciplina(String disciplinaId) {
        return matriculaRepository.findByDisciplinaIdAndNotaGreaterThanEqual(disciplinaId, 7.0)
                .stream().map(this::toDTO).toList();
    }

    public List<MatriculaResponseDTO> listarReprovadosPorDisciplina(String disciplinaId) {
        return matriculaRepository.findByDisciplinaIdAndNotaLessThan(disciplinaId, 7.0)
                .stream().map(this::toDTO).toList();
    }

    public List<MatriculaResponseDTO> listarPorDisciplina(String disciplinaId) {
        return matriculaRepository.findByDisciplinaId(disciplinaId)
                .stream().map(this::toDTO).toList();
    }

    public List<MatriculaResponseDTO> listarPorAluno(String alunoId) {
        return matriculaRepository.findByAlunoId(alunoId)
                .stream().map(this::toDTO).toList();
    }

    public List<MatriculaResponseDTO> listarTodas() {
        return matriculaRepository.findAll()
                .stream().map(this::toDTO).toList();
    }

    public List<MatriculaResponseDTO> listarAprovados() {
        return matriculaRepository.findByNotaGreaterThanEqual(7.0)
                .stream().map(this::toDTO).toList();
    }

    public List<MatriculaResponseDTO> listarReprovados() {
        return matriculaRepository.findByNotaLessThan(7.0)
                .stream().map(this::toDTO).toList();
    }

    private MatriculaResponseDTO toDTO(Matricula m) {
        return MatriculaResponseDTO.builder()
                .id(m.getId())
                .alunoId(m.getAluno().getId())
                .alunoNome(m.getAluno().getNome())
                .disciplinaId(m.getDisciplina().getId())
                .disciplinaNome(m.getDisciplina().getNome())
                .nota(m.getNota())
                .build();
    }
}
