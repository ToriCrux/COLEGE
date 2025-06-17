package org.example;

public enum InvoiceType {
    SIMPLE("Simples"),
    WITH_TAX("Com imposto"),
    UNKNOWN("Desconhecido");

    private final String description;

    InvoiceType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static InvoiceType fromInt(int type) {
        return switch (type) {
            case 1 -> SIMPLE;
            case 2 -> WITH_TAX;
            default -> UNKNOWN;
        };
    }
}
