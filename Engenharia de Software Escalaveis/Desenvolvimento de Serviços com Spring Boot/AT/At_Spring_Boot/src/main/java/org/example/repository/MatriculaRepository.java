package org.example.repository;

import org.example.domain.entity.Matricula;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MatriculaRepository extends MongoRepository<Matricula, String> {

    boolean existsByAlunoIdAndDisciplinaId(String alunoId, String disciplinaId);

    List<Matricula> findByDisciplinaId(String disciplinaId);

    List<Matricula> findByDisciplinaIdAndNotaGreaterThanEqual(String disciplinaId, Double nota);

    List<Matricula> findByDisciplinaIdAndNotaLessThan(String disciplinaId, Double nota);

    List<Matricula> findByAlunoId(String alunoId);

    List<Matricula> findByNotaGreaterThanEqual(Double nota);

    List<Matricula> findByNotaLessThan(Double nota);
}
