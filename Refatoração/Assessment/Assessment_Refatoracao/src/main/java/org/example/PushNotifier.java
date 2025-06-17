package org.example;

public class PushNotifier implements Notifier {
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending PUSH: " + message);
    }
}
