package org.example;

public class OrderItem {
    private String productName;
    private int quantity;
    private double unitPrice;

    public OrderItem(String productName, int quantity, double unitPrice) {
        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("Nome do produto não pode ser vazio.");
        }
        if (quantity <= 0 || unitPrice <= 0) {
            throw new IllegalArgumentException("Quantidade e preço devem ser maiores que zero.");
        }
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getSubtotal() {
        return quantity * unitPrice;
    }

    @Override
    public String toString() {
        return quantity + "x " + productName + " - R$" + unitPrice;
    }
}
