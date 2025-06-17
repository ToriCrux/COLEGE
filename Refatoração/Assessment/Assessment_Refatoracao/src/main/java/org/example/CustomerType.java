package org.example;

public enum CustomerType {
    REGULAR,
    PREMIUM;

    public static CustomerType fromInt(int type) {
        return switch (type) {
            case 1 -> REGULAR;
            case 2 -> PREMIUM;
            default -> throw new IllegalArgumentException("Tipo de cliente inválido: " + type);
        };
    }
}
