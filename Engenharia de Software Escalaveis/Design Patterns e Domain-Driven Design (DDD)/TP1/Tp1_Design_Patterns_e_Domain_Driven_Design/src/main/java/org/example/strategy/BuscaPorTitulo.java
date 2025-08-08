package org.example.strategy;

import org.example.biblioteca.ItemMidiateca;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class BuscaPorTitulo implements EstrategiaBusca {
    @Override
    public List<ItemMidiateca> buscar(List<ItemMidiateca> itens, String termo) {
        String t = termo.toLowerCase(Locale.ROOT);
        return itens.stream()
                .filter(i -> i.getTitulo().toLowerCase(Locale.ROOT).contains(t))
                .collect(Collectors.toList());
    }
}
