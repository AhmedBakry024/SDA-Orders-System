package sda.orderssystem.service.NotificationService;

/**
 * This is the abstract class for the Message class.
 * It contains the abstract method sendNotification(String message)
 * which will be implemented in the concrete classes.
 */
public abstract class Message {

    public String message;

    abstract void sendNotification(String message);
}
