package org.example.api.dto.disciplina;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DisciplinaResponseDTO {

    private String id;
    private String nome;
    private String codigo;
    private String professor_id;
}
