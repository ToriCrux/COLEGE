package org.example;

public class Invoice {
    private String clientName;
    private String clientEmail;
    private double amount;
    private InvoiceType type;

    public Invoice(String clientName, String clientEmail, double amount, int type) {
        this.clientName = clientName;
        this.clientEmail = clientEmail;
        this.amount = amount;
        this.type = InvoiceType.fromInt(type);
    }

    public void process() {
        if (clientEmail == null || !clientEmail.contains("@")) {
            System.out.println("Email inválido. Falha no envio.");
            return;
        }

        printInvoice();
        sendInvoiceByEmail();
    }

    private void printInvoice() {
        System.out.println("--- NOTA FISCAL ---");
        System.out.println("Cliente: " + clientName);
        System.out.println("Valor: R$ " + amount);
        System.out.println("Tipo: " + type.getDescription());
        System.out.println("---------------------");
    }

    private void sendInvoiceByEmail() {
        String content = "--- NOTA FISCAL ---\n" +
                "Cliente: " + clientName + "\n" +
                "Valor: R$ " + amount + "\n" +
                "Tipo: " + type.getDescription() + "\n" +
                "---------------------";
        System.out.println("Enviando nota fiscal para: " + clientEmail);
        enviarPorEmail(clientEmail, content);
    }

    private void enviarPorEmail(String email, String conteudo) {
        System.out.println("Enviando email para: " + email);
        System.out.println("Conteúdo:\n" + conteudo);
    }
}

