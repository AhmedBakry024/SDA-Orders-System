package sda.orderssystem.service.NotificationService;

public class EmailMessage extends Message {

    static int emailCount = 0;
    @Override
    public void sendNotification(String message) {

        this.message = message;
        emailCount++;
    }
}
