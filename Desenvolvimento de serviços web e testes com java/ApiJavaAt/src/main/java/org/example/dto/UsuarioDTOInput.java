package org.example.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UsuarioDTOInput {
    private int id;
    private String nome;
    private String senha;
}
