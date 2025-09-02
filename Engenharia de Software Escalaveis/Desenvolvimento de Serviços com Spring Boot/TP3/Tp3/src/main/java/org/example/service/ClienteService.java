package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.Cliente;
import org.example.exception.ResourceNotFoundException;
import org.example.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClienteService {

    private final ClienteRepository repo;

    public List<Cliente> list() {
        return repo.findAll();
    }

    public Cliente get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente " + id + " não encontrado"));
    }

    @Transactional
    public Cliente create(Cliente c) {
        return repo.save(c);
    }

    @Transactional
    public Cliente update(Long id, Cliente novo) {
        Cliente c = get(id);
        c.setNome(novo.getNome());
        c.setEmail(novo.getEmail());
        c.setDocumento(novo.getDocumento());
        c.setTelefone(novo.getTelefone());
        return repo.save(c);
    }

    @Transactional
    public void delete(Long id) {
        repo.deleteById(get(id).getId());
    }
}
