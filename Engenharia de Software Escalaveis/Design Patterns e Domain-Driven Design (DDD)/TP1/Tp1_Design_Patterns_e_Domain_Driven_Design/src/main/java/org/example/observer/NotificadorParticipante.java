package org.example.observer;

import org.example.biblioteca.EmprestimoMidiateca;

public class NotificadorParticipante implements Observador {
    @Override
    public void notificar(EmprestimoMidiateca e) {
        System.out.printf("[USUÁRIO] %s, o item '%s' está vencido desde %s.%n",
                e.getParticipante().getNome(), e.getItem().getTitulo(), e.getDataDevolucaoPrevista());
    }
}
