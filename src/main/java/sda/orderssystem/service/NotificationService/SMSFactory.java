package sda.orderssystem.service.NotificationService;

import java.util.Random;
import sda.orderssystem.model.*;
import sda.orderssystem.repository.UsersDatabase;

/**
 * This is the SMSFactory class that extends the ChannelFactory class
 * and implements the createNotification method.
 * It is used to create SMS notifications.
 * 
 * @see ChannelFactory
 */
public class SMSFactory extends ChannelFactory {

    public NotificationQueue notificationQueue = NotificationQueue.getInstance();

    /**
     * This method creates the SMS notifications.
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
                            new SMSMessage(
                                    messageTempelate2(user.getName(), product.getProductName()) + " This is an SMS."));
                } else {
                    notificationQueue.addNotification(
                            new SMSMessage(
                                    messageTempelate1(user.getName(), product.getProductName(), order.getStatus())
                                            + " This is an SMS."));
                }

            }
        }
        // This is the message that will be sent to the user when the order is shipped.
        // It will be sent for each product in the order.
        // If the order is a compound order, it will be sent for each product in each child order.
        if (order.getStatus().equals("Shipped")) {
            if (order instanceof CompoundOrder) {
                for (Order child : order.getChildren()) {
                    for (Product product : child.getProducts()) {
                        notificationQueue.addNotification(
                                new SMSMessage(messageTempelate3(user.getName(), product.getProductName(),
                                        product.getVendor()) + " This is an SMS."));
                    }
                }
            }
            for (Product product : order.getProducts()) {
                notificationQueue.addNotification(
                        new SMSMessage(messageTempelate3(user.getName(), product.getProductName(), product.getVendor())
                                + " This is an SMS."));
            }
        }
        return true;
    }
}
