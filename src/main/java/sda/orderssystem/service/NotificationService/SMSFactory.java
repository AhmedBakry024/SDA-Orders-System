package sda.orderssystem.service.NotificationService;

import java.util.Random;
import sda.orderssystem.model.*;
import sda.orderssystem.repository.UsersDatabase;

public class SMSFactory extends ChannelFactory {
    
    public NotificationQueue notificationQueue = NotificationQueue.getInstance();
    @Override
    public boolean createNotification(Order order) {
        Random random = new Random();
        int randomInt = random.nextInt(1);
        User user = UsersDatabase.getInstance().users.get(order.getCustomerID());
        if (order.getStatus().equals("Placed")) {
            for (Product product : order.getProducts()) {
                if (randomInt == 0) {
                    notificationQueue.addNotification(
                            new SMSMessage(messageTempelate2(user.getName(), product.getProductName()) + " This is an SMS."));
                } else {
                    notificationQueue.addNotification(
                            new SMSMessage(
                                    messageTempelate1(user.getName(), product.getProductName(), order.getStatus()) + " This is an SMS."));
                }

            }
        }
        if (order.getStatus().equals("Shipped")) {
            if(order instanceof CompoundOrder){
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
                        new SMSMessage(messageTempelate3(user.getName(), product.getProductName(), product.getVendor()) + " This is an SMS."));
            }
        }
        return true;
    }
}
