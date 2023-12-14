package org.example.service;
import org.example.dto.UsuarioDTOInput;
import org.example.dto.UsuarioDTOOutput;
import org.example.model.Usuario;
import org.modelmapper.ModelMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioService {
    private final List<Usuario> listaUsuarios = new ArrayList<>();
    private final ModelMapper modelMapper = new ModelMapper();

    public List<UsuarioDTOOutput> listar() {
        List<UsuarioDTOOutput> usuariosDTO = new ArrayList<>();
        listaUsuarios.forEach(usuario -> usuariosDTO.add(modelMapper.map(usuario, UsuarioDTOOutput.class)));
        return usuariosDTO;
    }

    public UsuarioDTOOutput buscar(int id) {
        Optional<Usuario> usuarioEncontrado = listaUsuarios.stream()
                .filter(usuario -> usuario.getId() == id)
                .findFirst();
        return usuarioEncontrado.map(u -> modelMapper.map(u, UsuarioDTOOutput.class)).orElse(null);
    }

    public void inserir(UsuarioDTOInput usuarioDTO) {
        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        listaUsuarios.add(usuario);
    }

    public void alterar(UsuarioDTOInput usuarioDTO) {
        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        listaUsuarios.stream()
                .filter(u -> u.getId() == usuario.getId())
                .findFirst()
                .ifPresent(u -> listaUsuarios.set(listaUsuarios.indexOf(u), usuario));
    }

    public void excluir(int id) {
        listaUsuarios.removeIf(usuario -> usuario.getId() == id);
    }
}
