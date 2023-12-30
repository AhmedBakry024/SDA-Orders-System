package sda.orderssystem.service.NotificationService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class NotificationQueue {

    private BlockingQueue<Message> notificationBlockingQueue = new LinkedBlockingQueue<>();

    public void addNotification(Message message) {
        try {
            notificationBlockingQueue.put(message);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void receiveMessage() {
        try {
            notificationBlockingQueue.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public Message removeNotification() {
        try {
            return notificationBlockingQueue.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
    }

    public ArrayList<Message> listAllNotifications() {
        ArrayList<Message> array = new ArrayList<>();
        Iterator<Message> iterator = notificationBlockingQueue.iterator();
        while (iterator.hasNext()) {
            Message message = iterator.next();
            array.add(message);
        }
        return array;
    }

}
