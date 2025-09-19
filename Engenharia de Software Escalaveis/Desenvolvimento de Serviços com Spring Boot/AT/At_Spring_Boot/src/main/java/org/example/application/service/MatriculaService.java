package org.example.application.service;

import lombok.RequiredArgsConstructor;
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

        // ✅ Verifica diretamente no banco se já existe matrícula com esse aluno e disciplina
        if (matriculaRepository.existsByAlunoIdAndDisciplinaId(aluno.getId(), disciplina.getId())) {
            throw new IllegalArgumentException("Este aluno já está matriculado nesta disciplina");
        }

        Matricula matricula = Matricula.builder()
                .aluno(aluno)
                .disciplina(disciplina)
                .build();

        Matricula salva = matriculaRepository.save(matricula);

        return MatriculaResponseDTO.builder()
                .id(salva.getId())
                .alunoId(aluno.getId())
                .alunoNome(aluno.getNome())
                .disciplinaId(disciplina.getId())
                .disciplinaNome(disciplina.getNome())
                .build();
    }

    public MatriculaResponseDTO atribuirNota(Long matriculaId, Double nota) {
        Matricula matricula = matriculaRepository.findById(matriculaId)
                .orElseThrow(() -> new IllegalArgumentException("Matrícula não encontrada"));

        matricula.setNota(nota);
        Matricula atualizada = matriculaRepository.save(matricula);

        return MatriculaResponseDTO.builder()
                .id(atualizada.getId())
                .alunoId(atualizada.getAluno().getId())
                .alunoNome(atualizada.getAluno().getNome())
                .disciplinaId(atualizada.getDisciplina().getId())
                .disciplinaNome(atualizada.getDisciplina().getNome())
                .build();
    }

    public List<MatriculaResponseDTO> listarAprovadosPorDisciplina(Long disciplinaId) {
        return matriculaRepository.findByDisciplinaIdAndNotaGreaterThanEqual(disciplinaId, 7.0)
                .stream()
                .map(m -> MatriculaResponseDTO.builder()
                        .id(m.getId())
                        .alunoId(m.getAluno().getId())
                        .alunoNome(m.getAluno().getNome())
                        .disciplinaId(m.getDisciplina().getId())
                        .disciplinaNome(m.getDisciplina().getNome())
                        .build())
                .toList();
    }

    public List<MatriculaResponseDTO> listarReprovadosPorDisciplina(Long disciplinaId) {
        return matriculaRepository.findByDisciplinaIdAndNotaLessThan(disciplinaId, 7.0)
                .stream()
                .map(m -> MatriculaResponseDTO.builder()
                        .id(m.getId())
                        .alunoId(m.getAluno().getId())
                        .alunoNome(m.getAluno().getNome())
                        .disciplinaId(m.getDisciplina().getId())
                        .disciplinaNome(m.getDisciplina().getNome())
                        .build())
                .toList();
    }
}
