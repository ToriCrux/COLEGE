package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.Departamento;
import org.example.exception.ResourceNotFoundException;
import org.example.repository.DepartamentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DepartamentoService {

    private final DepartamentoRepository repo;

    public List<Departamento> list() {
        return repo.findAll();
    }

    public Departamento get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Departamento " + id + " não encontrado"));
    }

    @Transactional
    public Departamento create(Departamento d) {
        return repo.save(d);
    }

    @Transactional
    public Departamento update(Long id, Departamento novo) {
        Departamento d = get(id);
        d.setNome(novo.getNome());
        return repo.save(d);
    }

    @Transactional
    public void delete(Long id) {
        repo.deleteById(get(id).getId());
    }
}
