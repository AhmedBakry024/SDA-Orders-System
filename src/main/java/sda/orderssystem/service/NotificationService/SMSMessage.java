package sda.orderssystem.service.NotificationService;

public class SMSMessage extends Message {
    
    static int smsCount = 0;

    public SMSMessage(String message) {
        sendNotification(message);
    }

    @Override
    public void sendNotification(String message) {
        this.message = message;
        smsCount++;
    }
}
