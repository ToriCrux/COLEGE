package org.example.api.dto.matricula;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MatriculaResponseDTO {

    private Long id;
    private Long alunoId;
    private String alunoNome;
    private Long disciplinaId;
    private String disciplinaNome;
    private Double nota;
}
