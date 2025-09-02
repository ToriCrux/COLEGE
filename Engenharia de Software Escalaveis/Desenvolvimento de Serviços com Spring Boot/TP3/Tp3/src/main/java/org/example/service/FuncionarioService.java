package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.Funcionario;
import org.example.exception.ResourceNotFoundException;
import org.example.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FuncionarioService {

    private final FuncionarioRepository repo;

    public List<Funcionario> list() {
        return repo.findAll();
    }

    public Funcionario get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionario " + id + " não encontrado"));
    }

    @Transactional
    public Funcionario create(Funcionario f) {
        return repo.save(f);
    }

    @Transactional
    public Funcionario update(Long id, Funcionario novo) {
        Funcionario f = get(id);
        f.setNome(novo.getNome());
        f.setEmail(novo.getEmail());
        f.setCargo(novo.getCargo());
        f.setSalario(novo.getSalario());
        f.setDataAdmissao(novo.getDataAdmissao());
        f.setDepartamento(novo.getDepartamento());
        return repo.save(f);
    }

    @Transactional
    public void delete(Long id) {
        repo.deleteById(get(id).getId());
    }
}
