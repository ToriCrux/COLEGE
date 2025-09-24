package org.example.repository;

import org.example.domain.entity.Professor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProfessorRepository extends MongoRepository<Professor, String> {
    Optional<Professor> findByEmail(String email);
}
