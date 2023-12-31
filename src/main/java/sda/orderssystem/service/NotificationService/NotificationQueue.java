package sda.orderssystem.service.NotificationService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class NotificationQueue {

    public BlockingQueue<Message> notificationBlockingQueue = new LinkedBlockingQueue<>();


    private static NotificationQueue notificationQueueInstance;

    public static boolean isFirst = true;

    private NotificationQueue() {
    }

    public static NotificationQueue getInstance() {
        if (notificationQueueInstance == null) {
            notificationQueueInstance = new NotificationQueue();
        }
        return notificationQueueInstance;
    }



    

    public void addNotification(Message message) {
        try {
            notificationBlockingQueue.put(message);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if (isFirst) {
            timer.scheduleAtFixedRate(automaticDeletion, 300000, 300000);
            isFirst = false;
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

    public void deleteAllNotifications() {
        notificationBlockingQueue.clear();
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

    Timer timer = new Timer();
    TimerTask automaticDeletion = new TimerTask() {
        @Override
        public void run() {
            deleteAllNotifications();
        }
    };

    

}
