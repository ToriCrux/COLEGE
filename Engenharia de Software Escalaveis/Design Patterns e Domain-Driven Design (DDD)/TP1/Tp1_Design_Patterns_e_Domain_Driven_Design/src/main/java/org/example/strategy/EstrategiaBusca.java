package org.example.strategy;

import org.example.biblioteca.ItemMidiateca;

import java.util.List;

public interface EstrategiaBusca {
    List<ItemMidiateca> buscar(List<ItemMidiateca> itens, String termo);
}
