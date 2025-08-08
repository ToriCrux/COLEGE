package org.example.biblioteca;

import java.time.LocalDate;
import java.util.Objects;

public class EmprestimoMidiateca {
    private final ItemMidiateca item;
    private final Participante participante;
    private final LocalDate dataEmprestimo;
    private final LocalDate dataDevolucaoPrevista;

    public EmprestimoMidiateca(ItemMidiateca item, Participante participante, LocalDate dataDevolucaoPrevista) {
        this.item = Objects.requireNonNull(item);
        this.participante = Objects.requireNonNull(participante);
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucaoPrevista = Objects.requireNonNull(dataDevolucaoPrevista);
    }

    public ItemMidiateca getItem() { return item; }
    public Participante getParticipante() { return participante; }
    public LocalDate getDataEmprestimo() { return dataEmprestimo; }
    public LocalDate getDataDevolucaoPrevista() { return dataDevolucaoPrevista; }

    public boolean estaVencido() {
        return LocalDate.now().isAfter(dataDevolucaoPrevista);
    }
}
