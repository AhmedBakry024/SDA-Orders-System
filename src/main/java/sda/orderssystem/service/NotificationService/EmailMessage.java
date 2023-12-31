package sda.orderssystem.service.NotificationService;


/**
 * This is the concrete product for the Message class.
 * It implements the abstract method sendNotification(String message)
 * which will be used to create the message.
 */
public class EmailMessage extends Message {

    static int emailCount = 0;
    @Override
    public void sendNotification(String message) {

        this.message = message;
        emailCount++;
    }
}
