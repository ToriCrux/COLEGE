package org.example.api.dto.matricula;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatriculaResponseDTO {

    private String id;
    private String alunoId;
    private String alunoNome;
    private String disciplinaId;
    private String disciplinaNome;
    private Double nota;
}
