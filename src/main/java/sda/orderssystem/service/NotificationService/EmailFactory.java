package sda.orderssystem.service.NotificationService;

import java.util.Random;
import sda.orderssystem.model.*;
import sda.orderssystem.repository.UsersDatabase;

/**
 * This class is the factory that will be used to create the email notifications.
 * It extends the ChannelFactory class.
 * @see ChannelFactory
 */
public class EmailFactory extends ChannelFactory {

    public NotificationQueue notificationQueue = NotificationQueue.getInstance();
    public TemplateCount templateCount = TemplateCount.getInstance();

    /**
     * This method creates the email notifications.
     * It takes an order as a parameter and returns true if the notification was created successfully.
     * @param order
     * @return boolean
     */
    @Override
    public boolean createNotification(Order order) {
        
        Random random = new Random();
        int randomInt = random.nextInt(1);
        User user = UsersDatabase.getInstance().users.get(order.getCustomerID());
        // This is the message that will be sent to the user when the order is placed.
        // It will be sent for each product in the order.
        if (order.getStatus().equals("Placed")) {
            for (Product product : order.getProducts()) {
                if (randomInt == 0) {
                    notificationQueue.addNotification(
                            new SMSMessage(messageTempelate2(user.getName(), product.getProductName())
                                    + " This is an Email."));
                templateCount.incrementCount(2);
                } else {
                    notificationQueue.addNotification(
                            new SMSMessage(
                                    messageTempelate1(user.getName(), product.getProductName(), order.getStatus())
                                            + " This is an Email."));
                    templateCount.incrementCount(1);
                }
            }
        }
        if (order.getStatus().equals("Shipped")) {
            // This is the message that will be sent to the user when the order is shipped.
            // It will be sent for each product in the order.
            if (order instanceof CompoundOrder) {
                for (Order child : order.getChildren()) {
                    for (Product product : child.getProducts()) {
                        notificationQueue.addNotification(
                                new SMSMessage(messageTempelate3(user.getName(), product.getProductName(),
                                        product.getVendor()) + " This is an Email."));
                        templateCount.incrementCount(3);
                    }
                }
            }
            for (Product product : order.getProducts()) {
                notificationQueue.addNotification(
                        new SMSMessage(messageTempelate3(user.getName(), product.getProductName(), product.getVendor())
                                + " This is an Email."));
                templateCount.incrementCount(3);
            }
        }
        return true;
    }
}
