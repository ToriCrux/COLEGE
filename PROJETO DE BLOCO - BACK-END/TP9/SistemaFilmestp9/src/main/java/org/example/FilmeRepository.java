package org.example;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmeRepository extends CrudRepository<Filme, Long> {
    List<Filme> findByUsuario(Usuario usuario);
    List<Filme> findByNotaFilmeGreaterThan(float nota);
}
