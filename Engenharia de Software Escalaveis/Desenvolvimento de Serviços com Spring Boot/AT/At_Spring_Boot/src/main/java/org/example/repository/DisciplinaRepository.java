package org.example.repository;

import org.example.domain.entity.Disciplina;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DisciplinaRepository extends MongoRepository<Disciplina, String> {
    Optional<Disciplina> findByCodigo(String codigo);
    boolean existsByCodigo(String codigo);
}
