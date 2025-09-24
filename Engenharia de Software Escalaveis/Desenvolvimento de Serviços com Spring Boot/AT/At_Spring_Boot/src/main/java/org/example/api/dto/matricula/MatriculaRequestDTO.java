package org.example.api.dto.matricula;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatriculaRequestDTO {

    @NotNull
    private String alunoId;

    @NotNull
    private String disciplinaId;
}
