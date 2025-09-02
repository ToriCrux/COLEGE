package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.Fornecedor;
import org.example.exception.ResourceNotFoundException;
import org.example.repository.FornecedorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FornecedorService {

    private final FornecedorRepository repo;

    public List<Fornecedor> list() {
        return repo.findAll();
    }

    public Fornecedor get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fornecedor " + id + " não encontrado"));
    }

    @Transactional
    public Fornecedor create(Fornecedor f) {
        return repo.save(f);
    }

    @Transactional
    public Fornecedor update(Long id, Fornecedor novo) {
        Fornecedor f = get(id);
        f.setNome(novo.getNome());
        f.setCnpj(novo.getCnpj());
        f.setEmail(novo.getEmail());
        f.setTelefone(novo.getTelefone());
        return repo.save(f);
    }

    @Transactional
    public void delete(Long id) {
        repo.deleteById(get(id).getId());
    }
}
