package org.example.main;

import org.example.biblioteca.*;
import org.example.facade.GestorBibliotecaFacade;
import org.example.observer.NotificadorAdmin;
import org.example.observer.NotificadorParticipante;
import org.example.strategy.BuscaPorAutor;
import org.example.strategy.BuscaPorTitulo;
import java.time.LocalDate;

public class AplicacaoPrincipal {
    public static void main(String[] args) {
        GestorBibliotecaFacade facade = new GestorBibliotecaFacade();
        GerenciadorCentral core = facade.core();

        //Observers
        core.adicionarObservador(new NotificadorAdmin());
        core.adicionarObservador(new NotificadorParticipante());

        //Usuários
        facade.cadastrarParticipante("u1", "Ana");
        facade.cadastrarParticipante("u2", "Bruno");

        //Itens (Factory)
        facade.cadastrarLivro("isbn-1", "Dom Casmurro", "Machado de Assis");
        facade.cadastrarLivro("isbn-2", "Capitães da Areia", "Jorge Amado");
        facade.cadastrarFilme("dvd-1", "Matrix", "Wachowski", "Ficção");

        //Strategy: busca por título
        core.definirEstrategiaBusca(new BuscaPorTitulo());
        System.out.println("\nBusca (titulo='ma'): " + core.buscarItens("ma") + "\n");

        //Strategy: busca por autor
        core.definirEstrategiaBusca(new BuscaPorAutor());
        System.out.println("Busca (autor='jorge'): " + core.buscarItens("jorge")+ "\n");

        //Empréstimo
        facade.emprestar("isbn-1", "u1", LocalDate.now().minusDays(1));
        facade.emprestar("dvd-1", "u2", LocalDate.now().plusDays(7));

        //Verifica vencidos e notifica observers
        core.checarVencidosENotificar();
    }
}
