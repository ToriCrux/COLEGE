package org.example;

public class EmailNotifier implements Notifier {
    @Override
    public void sendNotification(String message) {
        System.out.println("\nSending EMAIL: " + message);
    }
}
