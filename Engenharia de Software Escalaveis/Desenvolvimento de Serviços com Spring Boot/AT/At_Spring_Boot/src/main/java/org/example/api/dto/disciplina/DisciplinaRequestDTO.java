package org.example.api.dto.disciplina;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DisciplinaRequestDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String codigo;

    private Long professor_id;
}
