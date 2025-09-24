package org.example.repository;

import org.example.domain.entity.Aluno;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AlunoRepository extends MongoRepository<Aluno, String> {
    Optional<Aluno> findByCpf(String cpf);
    boolean existsByCpf(String cpf);
}
