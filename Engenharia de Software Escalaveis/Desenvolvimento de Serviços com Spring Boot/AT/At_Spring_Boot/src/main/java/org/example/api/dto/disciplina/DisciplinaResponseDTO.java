package org.example.api.dto.disciplina;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DisciplinaResponseDTO {

    private Long id;
    private String nome;
    private String codigo;
    private Long professor_id;
}
