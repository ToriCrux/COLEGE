package org.example;

public class NotificationService {

    private Notifier notifier;

    public NotificationService(Notifier notifier) {
        this.notifier = notifier;
    }

    public void notifyUser(String message) {
        notifier.sendNotification(message);
    }
}
