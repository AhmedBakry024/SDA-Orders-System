package sda.orderssystem.service.NotificationService;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class NotificationQueue {

    private BlockingQueue <Notification> notificationBlockingQueue = new LinkedBlockingQueue<>();

    public void addNotification(Notification notification) {
        try {
            notificationBlockingQueue.put(notification);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public String receiveMessage() {
        try {
            return notificationBlockingQueue.take().toString();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
    }

    public Notification removeNotification() {
        try {
            return notificationBlockingQueue.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
    }

}
