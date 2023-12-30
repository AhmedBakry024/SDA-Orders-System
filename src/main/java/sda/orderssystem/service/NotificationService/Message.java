package sda.orderssystem.service.NotificationService;

public abstract class Message {

    public String message;

    abstract void sendNotification(String message);
}
