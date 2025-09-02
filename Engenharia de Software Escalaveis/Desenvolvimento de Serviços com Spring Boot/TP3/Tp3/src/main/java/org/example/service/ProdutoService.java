package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.Produto;
import org.example.exception.ResourceNotFoundException;
import org.example.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProdutoService {

    private final ProdutoRepository repo;

    public List<Produto> list() {
        return repo.findAll();
    }

    public Produto get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto " + id + " não encontrado"));
    }

    @Transactional
    public Produto create(Produto p) {
        return repo.save(p);
    }

    @Transactional
    public Produto update(Long id, Produto novo) {
        Produto p = get(id);
        p.setTitulo(novo.getTitulo());
        p.setAutor(novo.getAutor());
        p.setIsbn(novo.getIsbn());
        p.setEditora(novo.getEditora());
        p.setAnoPublicacao(novo.getAnoPublicacao());
        p.setPreco(novo.getPreco());
        p.setEstoque(novo.getEstoque());
        p.setDepartamento(novo.getDepartamento());
        return repo.save(p);
    }

    @Transactional
    public void delete(Long id) {
        repo.deleteById(get(id).getId());
    }
}
