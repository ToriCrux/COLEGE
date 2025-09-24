package org.example.domain.entity;

import jakarta.validation.constraints.*;
import lombok.*;
import org.example.domain.valueobject.Endereco;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "alunos")
public class Aluno {

    @Id
    private String id;

    @NotBlank
    private String nome;

    @NotBlank
    @Indexed(unique = true)
    private String cpf;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String telefone;

    private Endereco endereco;
}
