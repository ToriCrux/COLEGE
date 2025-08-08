package org.example.observer;

import org.example.biblioteca.EmprestimoMidiateca;

public class NotificadorAdmin implements Observador {
    @Override
    public void notificar(EmprestimoMidiateca e) {
        System.out.printf("[ADMIN] Empréstimo vencido: item='%s', usuário='%s', vencido em %s%n",
                e.getItem().getTitulo(), e.getParticipante().getNome(), e.getDataDevolucaoPrevista());
    }
}
