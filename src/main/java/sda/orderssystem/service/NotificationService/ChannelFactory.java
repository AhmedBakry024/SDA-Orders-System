package sda.orderssystem.service.NotificationService;

public abstract class ChannelFactory {
    public abstract Message createNotification(String message);
    public abstract Message deleteNotification(String message);

    public Message makeMsg(String message) {
        Message msg = createNotification(message);
        msg.sendNotification(message);
        msg.deleteNotification(message);
        return msg;
    }

}
