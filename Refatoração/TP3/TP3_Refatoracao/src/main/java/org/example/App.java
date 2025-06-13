package org.example;

public class App {
    public static void main(String[] args) {
        Client client = new Client("João", "joao@email.com");
        Order order = new Order(client);

        order.addItem(new OrderItem("Notebook", 1, 3500.0));
        order.addItem(new OrderItem("Mouse", 2, 80.0));

        InvoicePrinter.print(order);
        order.confirmOrder();
    }
}
