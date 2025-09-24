package org.example.domain.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "disciplinas")
public class Disciplina {

    @Id
    private String id;

    @NotBlank
    private String nome;

    @NotBlank
    @Indexed(unique = true)
    private String codigo;

    @DBRef
    private Professor professor;
}
