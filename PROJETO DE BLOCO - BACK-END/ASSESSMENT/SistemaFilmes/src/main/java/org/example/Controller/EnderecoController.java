package org.example.Controller;
import org.example.Endereco.Endereco;
import org.example.Endereco.EnderecoRepository;
import org.example.Api.ResourceNotFoundException;
import org.example.Usuario.Usuario;
import org.example.Usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Endereco> getAllEnderecos() {
        return (List<Endereco>) enderecoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Endereco getEnderecoById(@PathVariable Long id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado com o ID: " + id));
    }

    @PostMapping("/usuario/{usuarioId}")
    public Endereco createEnderecoForUsuario(@PathVariable Long usuarioId, @RequestBody Endereco novoEndereco) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o ID: " + usuarioId));

        novoEndereco.setUsuario(usuario);

        if (usuario.getEnderecos() == null) {
            usuario.setEnderecos(new ArrayList<>());
        }
        usuario.getEnderecos().add(novoEndereco);

        usuarioRepository.save(usuario);

        return enderecoRepository.save(novoEndereco);
    }

    @PutMapping("/{id}")
    public Endereco updateEndereco(@PathVariable Long id, @RequestBody Endereco novoEndereco) {
        return enderecoRepository.findById(id)
                .map(endereco -> {
                    endereco.setCep(novoEndereco.getCep());
                    endereco.setLogradouro(novoEndereco.getLogradouro());
                    endereco.setBairro(novoEndereco.getBairro());
                    endereco.setCidade(novoEndereco.getCidade());
                    endereco.setEstado(novoEndereco.getEstado());

                    return enderecoRepository.save(endereco);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado com o ID: " + id));
    }

    @PostMapping("/incluir")
    public Endereco incluirEndereco(@RequestBody Endereco novoEndereco) {
        return enderecoRepository.save(novoEndereco);
    }

    @DeleteMapping("/{id}")
    public void deleteEndereco(@PathVariable Long id) {
        enderecoRepository.deleteById(id);
    }
}
