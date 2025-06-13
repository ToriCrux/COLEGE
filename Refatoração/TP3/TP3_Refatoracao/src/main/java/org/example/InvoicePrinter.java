package org.example;

public class InvoicePrinter {

    public static void print(Order order) {
        printClientInfo(order);
        printItems(order);
        printTotals(order);
    }

    private static void printClientInfo(Order order) {
        System.out.println("Cliente: " + order.getClient().getName());
    }

    private static void printItems(Order order) {
        for (OrderItem item : order.getItems()) {
            System.out.println(item);
        }
    }

    private static void printTotals(Order order) {
        System.out.println("Subtotal: R$" + order.getSubtotal());
        System.out.println("Desconto: R$" + order.getDiscount());
        System.out.println("Total final: R$" + order.getTotalWithDiscount());
    }
}
