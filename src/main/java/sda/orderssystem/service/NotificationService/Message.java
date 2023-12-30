package sda.orderssystem.service.NotificationService;

public abstract class Message {

     abstract void sendNotification(String message);
    abstract void deleteNotification(String message);

    abstract void shipNotification(String message);
    abstract void placementNotification(String message);
}
