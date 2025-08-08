package org.example.facade;

import org.example.biblioteca.*;
import org.example.factory.CriadorItem;
import org.example.factory.FabricaItemConcreta;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GestorBibliotecaFacade {

    private final GerenciadorCentral gc = GerenciadorCentral.getInstancia();
    private final CriadorItem fabrica = new FabricaItemConcreta();

    //Usuários
    public void cadastrarParticipante(String id, String nome) {
        gc.registrarParticipante(new Participante(id, nome));
    }

    //Itens (via Factory)
    public ItemMidiateca cadastrarLivro(String id, String titulo, String autor) {
        Map<String, String> dados = new HashMap<>();
        dados.put("id", id); dados.put("titulo", titulo); dados.put("autor", autor);
        ItemMidiateca item = fabrica.criarItem("livro", dados);
        gc.adicionarItem(item);
        return item;
    }

    public ItemMidiateca cadastrarFilme(String id, String titulo, String diretor, String genero) {
        Map<String, String> dados = new HashMap<>();
        dados.put("id", id); dados.put("titulo", titulo);
        dados.put("diretor", diretor); dados.put("genero", genero);
        ItemMidiateca item = fabrica.criarItem("filme", dados);
        gc.adicionarItem(item);
        return item;
    }

    //Empréstimos
    public EmprestimoMidiateca emprestar(String idItem, String idParticipante, LocalDate devolucao) {
        return gc.emprestarItem(idItem, idParticipante, devolucao);
    }

    public boolean devolver(String idItem, String idParticipante) {
        return gc.devolverItem(idItem, idParticipante);
    }

    //Conveniências
    public Optional<ItemMidiateca> buscarPorId(String id) { return gc.obterItemPorId(id); }
    public Optional<Participante> obterParticipante(String id) { return gc.obterParticipantePorId(id); }

    public GerenciadorCentral core() { return gc; } // expõe o núcleo quando necessário (útil p/ configurar Strategy/Observer)
}
