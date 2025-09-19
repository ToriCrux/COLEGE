package org.example.repository;

import org.example.domain.entity.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

    List<Matricula> findByDisciplinaId(Long disciplinaId);

    List<Matricula> findByDisciplinaIdAndNotaGreaterThanEqual(Long disciplinaId, Double nota);

    List<Matricula> findByDisciplinaIdAndNotaLessThan(Long disciplinaId, Double nota);

    boolean existsByAlunoIdAndDisciplinaId(Long alunoId, Long disciplinaId);
}
