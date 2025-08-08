package org.example.biblioteca;

import java.util.Objects;

public class FilmeMidiateca implements ItemMidiateca {
    private final String id;
    private final String titulo;
    private final String diretor;
    private final String genero;

    public FilmeMidiateca(String id, String titulo, String diretor, String genero) {
        this.id = Objects.requireNonNull(id);
        this.titulo = Objects.requireNonNull(titulo);
        this.diretor = Objects.requireNonNull(diretor);
        this.genero = Objects.requireNonNull(genero);
    }

    @Override public String getId() { return id; }
    @Override public String getTitulo() { return titulo; }
    public String getDiretor() { return diretor; }
    public String getGenero() { return genero; }

    @Override public String toString() {
        return "Filme{" + "id='" + id + '\'' + ", titulo='" + titulo + '\'' + ", diretor='" + diretor + '\'' + ", genero='" + genero + '\'' + '}';
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FilmeMidiateca that)) return false;
        return id.equals(that.id);
    }

    @Override public int hashCode() { return Objects.hash(id); }
}
