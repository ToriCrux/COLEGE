package org.example;
import jakarta.persistence.*;

@Entity
@Table(name = "Filmes")
public class Filme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_filme")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private Usuario usuario;

    @Column(name = "nome_filme")
    private String nome;

    @Column(name = "nota_filme")
    private float notaFilme;

    @Column(name = "recomendacao_filme")
    private String recomendacaoFilme;

    public Filme() {
    }

    public Filme(Usuario usuario, String nome, float nota, String recomendacao) {
        this.usuario = usuario;
        this.nome = nome;
        this.notaFilme = nota;
        this.recomendacaoFilme = recomendacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNomeFilme() {
        return nome;
    }

    public void setNomeFilme(String nomeFilme) {
        this.nome = nomeFilme;
    }

    public float getNotaFilme() {
        return notaFilme;
    }

    public void setNotaFilme(float notaFilme) {
        this.notaFilme = notaFilme;
    }

    public String getRecomendacaoFilme() {
        return recomendacaoFilme;
    }

    public void setRecomendacaoFilme(String recomendacaoFilme) {
        this.recomendacaoFilme = recomendacaoFilme;
    }
}
