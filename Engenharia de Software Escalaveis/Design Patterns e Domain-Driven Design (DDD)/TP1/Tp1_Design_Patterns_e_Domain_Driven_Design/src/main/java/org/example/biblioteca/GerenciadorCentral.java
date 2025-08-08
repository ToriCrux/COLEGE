package org.example.biblioteca;

import org.example.observer.Observador;
import org.example.strategy.EstrategiaBusca;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class GerenciadorCentral {
    //Singleton
    private static volatile GerenciadorCentral instancia;
    private GerenciadorCentral() {}
    public static GerenciadorCentral getInstancia() {
        if (instancia == null) {
            synchronized (GerenciadorCentral.class) {
                if (instancia == null) instancia = new GerenciadorCentral();
            }
        }
        return instancia;
    }

    //Estado principal
    private final Map<String, ItemMidiateca> itens = new HashMap<>();
    private final Map<String, Participante> participantes = new HashMap<>();
    private final List<EmprestimoMidiateca> emprestimos = new ArrayList<>();
    private final List<Observador> observadores = new ArrayList<>();

    //Strategy
    private EstrategiaBusca estrategiaBusca;

    //Cadastro de itens e participantes
    public void adicionarItem(ItemMidiateca item) { itens.put(item.getId(), item); }
    public void removerItem(String idItem) { itens.remove(idItem); }

    public void registrarParticipante(Participante p) { participantes.put(p.getId(), p); }
    public void removerParticipante(String id) { participantes.remove(id); }

    public Optional<ItemMidiateca> obterItemPorId(String id) { return Optional.ofNullable(itens.get(id)); }
    public Optional<Participante> obterParticipantePorId(String id) { return Optional.ofNullable(participantes.get(id)); }

    public Collection<ItemMidiateca> listarItens() { return Collections.unmodifiableCollection(itens.values()); }
    public Collection<Participante> listarParticipantes() { return Collections.unmodifiableCollection(participantes.values()); }
    public List<EmprestimoMidiateca> listarEmprestimos() { return Collections.unmodifiableList(emprestimos); }

    //Empréstimos
    public EmprestimoMidiateca emprestarItem(String idItem, String idParticipante, LocalDate dataDevolucao) {
        ItemMidiateca item = itens.get(idItem);
        Participante p = participantes.get(idParticipante);
        if (item == null || p == null) throw new IllegalArgumentException("Item ou participante inexistente.");

        boolean jaEmprestado = emprestimos.stream().anyMatch(e -> e.getItem().getId().equals(idItem));
        if (jaEmprestado) throw new IllegalStateException("Item já emprestado.");

        EmprestimoMidiateca emp = new EmprestimoMidiateca(item, p, dataDevolucao);
        emprestimos.add(emp);
        return emp;
    }

    public boolean devolverItem(String idItem, String idParticipante) {
        return emprestimos.removeIf(e -> e.getItem().getId().equals(idItem) && e.getParticipante().getId().equals(idParticipante));
    }

    //Observer
    public void adicionarObservador(Observador o) { observadores.add(o); }
    public void removerObservador(Observador o) { observadores.remove(o); }

    public List<EmprestimoMidiateca> checarVencidosENotificar() {
        List<EmprestimoMidiateca> vencidos = emprestimos.stream()
                .filter(EmprestimoMidiateca::estaVencido)
                .collect(Collectors.toList());
        vencidos.forEach(this::notificarObservadores);
        return vencidos;
    }

    private void notificarObservadores(EmprestimoMidiateca emprestimo) {
        for (Observador o : observadores) o.notificar(emprestimo);
    }

    //Strategy
    public void definirEstrategiaBusca(EstrategiaBusca estrategia) { this.estrategiaBusca = estrategia; }
    public List<ItemMidiateca> buscarItens(String termo) {
        if (estrategiaBusca == null) return List.of();
        return estrategiaBusca.buscar(new ArrayList<>(itens.values()), termo);
    }
}
