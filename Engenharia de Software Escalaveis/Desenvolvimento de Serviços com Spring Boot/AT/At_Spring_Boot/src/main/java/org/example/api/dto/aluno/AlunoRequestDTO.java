package org.example.api.dto.aluno;

import jakarta.validation.constraints.*;
import lombok.*;
import org.example.domain.valueobject.Endereco;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlunoRequestDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String cpf;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String telefone;

    @NotNull
    private Endereco endereco;
}
