package org.example;

public class Main {
    public static void main(String[] args) {
        Notifier emailNotifier = new EmailNotifier();
        NotificationService emailService = new NotificationService(emailNotifier);
        emailService.notifyUser("Bem-vindo a nossa plataforma!");

        Notifier smsNotifier = new SmsNotifier();
        NotificationService smsService = new NotificationService(smsNotifier);
        smsService.notifyUser("Seu código é 9812.");

        Notifier pushNotifier = new PushNotifier();
        NotificationService pushService = new NotificationService(pushNotifier);
        pushService.notifyUser("Você tem uma nova mensagem.");
    }
}

