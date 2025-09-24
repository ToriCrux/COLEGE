package org.example.domain.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "matriculas")
public class Matricula {

    @Id
    private String id;

    @DBRef
    private Aluno aluno;

    @DBRef
    private Disciplina disciplina;

    private Double nota;
}
