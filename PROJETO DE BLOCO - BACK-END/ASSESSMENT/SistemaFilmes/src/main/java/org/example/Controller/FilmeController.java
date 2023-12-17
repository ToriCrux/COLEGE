package org.example.Controller;
import org.example.Filme.Filme;
import org.example.Filme.FilmeRepository;
import org.example.Api.ResourceNotFoundException;
import org.example.Usuario.Usuario;
import org.example.Usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Filme> getAllFilmes() {
        return (List<Filme>) filmeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Filme getFilmeById(@PathVariable Long id) {
        return filmeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Filme não encontrado com o ID: " + id));
    }

    @PostMapping("/usuario/{usuarioId}")
    public Filme createFilmeForUsuario(@PathVariable Long usuarioId, @RequestBody Filme novoFilme) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o ID: " + usuarioId));

        novoFilme.setUsuario(usuario);
        return filmeRepository.save(novoFilme);
    }

    @PutMapping("/{id}")
    public Filme updateFilme(@PathVariable Long id, @RequestBody Filme novoFilme) {
        return filmeRepository.findById(id)
                .map(filme -> {
                    filme.setNomeFilme(novoFilme.getNomeFilme());
                    filme.setNotaFilme(novoFilme.getNotaFilme());
                    filme.setRecomendacaoFilme(novoFilme.getRecomendacaoFilme());

                    return filmeRepository.save(filme);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Filme não encontrado com o ID: " + id));
    }

    @PostMapping("/incluir")
    public Filme incluirFilme(@RequestBody Filme novoFilme) {
        return filmeRepository.save(novoFilme);
    }

    @DeleteMapping("/{id}")
    public void deleteFilme(@PathVariable Long id) {
        filmeRepository.deleteById(id);
    }
}
