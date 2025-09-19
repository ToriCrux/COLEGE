package org.example.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.example.domain.valueobject.Endereco;
import lombok.*;

@Embeddable
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
@Table(name = "alunos")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    @Column(unique = true)
    private String cpf;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String telefone;

    @Embedded
    private Endereco endereco;
}
