package org.example.api.dto.aluno;

import lombok.*;
import org.example.domain.valueobject.Endereco;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlunoResponseDTO {

    private String id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private Endereco endereco;
}
