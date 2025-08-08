package org.example.strategy;

import org.example.biblioteca.ItemMidiateca;
import org.example.biblioteca.ObraLiteraria;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class BuscaPorAutor implements EstrategiaBusca {
    @Override
    public List<ItemMidiateca> buscar(List<ItemMidiateca> itens, String termo) {
        String t = termo.toLowerCase(Locale.ROOT);
        return itens.stream()
                .filter(i -> i instanceof ObraLiteraria)
                .map(i -> (ObraLiteraria) i)
                .filter(l -> l.getAutor().toLowerCase(Locale.ROOT).contains(t))
                .collect(Collectors.toList());
    }
}
