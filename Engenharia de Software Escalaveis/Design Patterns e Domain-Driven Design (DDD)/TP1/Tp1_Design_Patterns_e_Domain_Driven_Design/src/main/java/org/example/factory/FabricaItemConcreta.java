package org.example.factory;

import org.example.biblioteca.FilmeMidiateca;
import org.example.biblioteca.ItemMidiateca;
import org.example.biblioteca.ObraLiteraria;
import java.util.Map;

public class FabricaItemConcreta extends CriadorItem {

    @Override
    public ItemMidiateca criarItem(String tipo, Map<String, String> dados) {
        String t = tipo.toLowerCase();
        return switch (t) {
            case "livro" -> new ObraLiteraria(
                    dados.get("id"),
                    dados.get("titulo"),
                    dados.get("autor")
            );
            case "filme" -> new FilmeMidiateca(
                    dados.get("id"),
                    dados.get("titulo"),
                    dados.get("diretor"),
                    dados.getOrDefault("genero", "Desconhecido")
            );
            default -> throw new IllegalArgumentException("Tipo de item não suportado: " + tipo);
        };
    }
}
