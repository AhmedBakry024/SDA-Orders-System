package sda.orderssystem.service.NotificationService;

public abstract class Channel {
    public abstract IMessage createNotification(String message);
    public abstract IMessage deleteNotification(String message);

    public IMessage makeMsg(String message) {
        IMessage msg = createNotification(message);
        msg.sendNotification(message);
        msg.deleteNotification(message);
        return msg;
    }

}
