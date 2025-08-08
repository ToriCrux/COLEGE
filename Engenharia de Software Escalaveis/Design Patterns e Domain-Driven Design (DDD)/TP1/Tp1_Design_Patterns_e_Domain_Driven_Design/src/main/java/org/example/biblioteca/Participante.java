package org.example.biblioteca;

import java.util.Objects;

public class Participante {
    private final String id;
    private final String nome;

    public Participante(String id, String nome) {
        this.id = Objects.requireNonNull(id);
        this.nome = Objects.requireNonNull(nome);
    }

    public String getId() { return id; }
    public String getNome() { return nome; }

    @Override public String toString() { return nome + " (" + id + ")"; }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Participante that)) return false;
        return id.equals(that.id);
    }

    @Override public int hashCode() { return Objects.hash(id); }
}
