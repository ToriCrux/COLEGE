package org.example;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private Client client;
    private double discountRate = 0.1;
    private List<OrderItem> items = new ArrayList<>();

    public Order(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo.");
        }
        this.client = client;
    }

    public void addItem(OrderItem item) {
        if (item == null || item.getQuantity() <= 0 || item.getUnitPrice() <= 0) {
            throw new IllegalArgumentException("Item inválido.");
        }
        items.add(item);
    }

    public Client getClient() {
        return client;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public double getSubtotal() {
        return items.stream()
                .mapToDouble(OrderItem::getSubtotal)
                .sum();
    }

    public double getDiscount() {
        return getSubtotal() * discountRate;
    }

    public double getTotalWithDiscount() {
        return getSubtotal() - getDiscount();
    }

    public void confirmOrder() {
        if (items.isEmpty()) {
            throw new IllegalStateException("Pedido não pode ser confirmado sem itens.");
        }
        sendConfirmationEmail();
    }

    private void sendConfirmationEmail() {
        Email email = buildEmailConfirmation();
        EmailService.send(email);
    }

    private Email buildEmailConfirmation() {
        return Email.buildConfirmationEmail(client);
    }
}
