package org.example.api.dto.matricula;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class NotaRequestDTO {

    @NotNull
    @Min(0)
    @Max(10)
    private Double nota;
}
