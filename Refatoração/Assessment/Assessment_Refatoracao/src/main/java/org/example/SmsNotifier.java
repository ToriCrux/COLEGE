package org.example;

public class SmsNotifier implements Notifier {
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending SMS: " + message);
    }
}
