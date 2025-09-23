package org.example.api.dto.professor;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ProfessorResponseDTO {
    private Long id;
    private String nome;
    private String email;
}
