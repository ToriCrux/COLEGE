package org.example;

public class EmailService {
    public static void send(Email email) {
        System.out.println("Enviando e-mail para " + email.getTo() + ": " + email.getSubject());
        System.out.println("Corpo: " + email.getBody());
    }
}
