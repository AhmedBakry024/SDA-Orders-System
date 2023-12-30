package sda.orderssystem.service.NotificationService;

public interface IMessage {

     void sendNotification(String message);
    void deleteNotification(String message);

    void shipNotification(String message);
    void placementNotification(String message);
}
