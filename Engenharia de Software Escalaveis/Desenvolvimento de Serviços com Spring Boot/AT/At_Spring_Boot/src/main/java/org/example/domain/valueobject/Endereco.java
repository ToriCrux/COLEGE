package org.example.domain.valueobject;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Endereco {

    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
}
