package sda.orderssystem.service.NotificationService;

/**
 * This is the concrete product for the Message class.
 * It implements the abstract method sendNotification(String message)
 * which will be used to create the message.
 */
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
