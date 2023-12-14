package org.example.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.UsuarioDTOInput;
import org.example.dto.UsuarioDTOOutput;
import org.example.service.UsuarioService;
import spark.Request;
import spark.Response;
import java.util.List;
import static spark.Spark.*;

public class UsuarioController {
    private final UsuarioService usuarioService = new UsuarioService();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void respostasRequisicoes() {
        get("/usuarios", this::listarUsuarios, objectMapper::writeValueAsString);
        get("/usuarios/:id", this::buscarUsuario, objectMapper::writeValueAsString);
        delete("/usuarios/:id", this::excluirUsuario, objectMapper::writeValueAsString);
        post("/usuarios", this::inserirUsuario, objectMapper::writeValueAsString);
        put("/usuarios", this::alterarUsuario, objectMapper::writeValueAsString);
    }

    private List<UsuarioDTOOutput> listarUsuarios(Request request, Response response) {
        response.type("application/json");
        return usuarioService.listar();
    }

    private UsuarioDTOOutput buscarUsuario(Request request, Response response) {
        response.type("application/json");
        int id = Integer.parseInt(request.params(":id"));
        UsuarioDTOOutput usuario = usuarioService.buscar(id);
        response.status(usuario != null ? 200 : 404);
        return usuario;
    }

    private int inserirUsuario(Request request, Response response) throws Exception {
        response.type("application/json");
        UsuarioDTOInput usuarioDTO = objectMapper.readValue(request.body(), UsuarioDTOInput.class);
        usuarioService.inserir(usuarioDTO);
        response.status(201);
        return response.status();
    }

    private String alterarUsuario(Request request, Response response) throws Exception {
        response.type("application/json");
        UsuarioDTOInput usuarioDTO = objectMapper.readValue(request.body(), UsuarioDTOInput.class);
        usuarioService.alterar(usuarioDTO);
        response.status(200);
        return "";
    }

    private String excluirUsuario(Request request, Response response) {
        response.type("application/json");
        int id = Integer.parseInt(request.params(":id"));
        usuarioService.excluir(id);
        response.status(204);
        return "";
    }
}
