package org.example.api.dto.matricula;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MatriculaRequestDTO {

    @NotNull
    private Long alunoId;

    @NotNull
    private Long disciplinaId;
}
