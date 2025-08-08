package org.example.factory;

import org.example.biblioteca.ItemMidiateca;

import java.util.Map;

public abstract class CriadorItem {
    /**
     * @param tipo   Ex.: "livro", "filme"
     * @param dados  Atributos necessários (ex.: id, titulo, autor, diretor...)
     */
    public abstract ItemMidiateca criarItem(String tipo, Map<String, String> dados);
}
