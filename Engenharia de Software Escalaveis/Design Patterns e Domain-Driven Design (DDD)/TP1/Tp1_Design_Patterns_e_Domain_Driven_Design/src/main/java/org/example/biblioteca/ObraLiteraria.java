package org.example.biblioteca;

import java.util.Objects;

public class ObraLiteraria implements ItemMidiateca {
    private final String id;
    private final String titulo;
    private final String autor;

    public ObraLiteraria(String id, String titulo, String autor) {
        this.id = Objects.requireNonNull(id);
        this.titulo = Objects.requireNonNull(titulo);
        this.autor = Objects.requireNonNull(autor);
    }

    @Override public String getId() { return id; }
    @Override public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }

    @Override public String toString() {
        return "Livro{" + "id='" + id + '\'' + ", titulo='" + titulo + '\'' + ", autor='" + autor + '\'' + '}';
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ObraLiteraria that)) return false;
        return id.equals(that.id);
    }

    @Override public int hashCode() { return Objects.hash(id); }
}
