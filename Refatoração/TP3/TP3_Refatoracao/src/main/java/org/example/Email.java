package org.example;

public class Email {
    private String to;
    private String subject;
    private String body;

    public Email(String to, String subject, String body) {
        this.to = to;
        this.subject = subject;
        this.body = body;
    }

    public String getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public static Email buildConfirmationEmail(Client client) {
        String subject = "Pedido recebido!";
        String body = "Olá " + client.getName() + ",\n\nSeu pedido foi recebido com sucesso. Obrigado pela compra!";
        return new Email(client.getEmail(), subject, body);
    }
}
