package org.example.observer;

import org.example.biblioteca.EmprestimoMidiateca;

public interface Observador {
    void notificar(EmprestimoMidiateca emprestimoVencido);
}
