package org.example.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "produto")
public class Produto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Column(nullable = false, length = 160)
    private String titulo;

    @NotBlank @Column(nullable = false, length = 120)
    private String autor;

    @NotBlank @Column(nullable = false, unique = true, length = 20)
    private String isbn;

    @NotBlank @Column(nullable = false, length = 100)
    private String editora;

    @Min(1500) @Max(2100)
    @Column(nullable = false)
    private Integer anoPublicacao;

    @Positive @Column(nullable = false, precision = 14, scale = 2)
    private BigDecimal preco;

    @PositiveOrZero @Column(nullable = false)
    private Integer estoque;

    @ManyToOne(optional = false)
    private Departamento departamento;
}
