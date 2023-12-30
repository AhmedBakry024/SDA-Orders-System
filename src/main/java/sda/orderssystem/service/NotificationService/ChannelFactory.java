package sda.orderssystem.service.NotificationService;

import sda.orderssystem.model.Order;

public abstract class ChannelFactory {

    public abstract boolean createNotification(Order order);

    public String messageTempelate1(String CustomerName, String OrderId, String OrderStatus) {
        String message = "Dear " + CustomerName + ", your order " + OrderId + " is " + OrderStatus
                + ". Thank you for shopping with us.";
        return message;
    }

    public String messageTempelate2(String CustomerName, String Product) {
        String message = "Dear " + CustomerName + ", your product " + Product
                + " is confirmed. Thank you for shopping with us.";
        return message;
    }
    
    public String messageTempelate3(String CustomerName, String Product, String Vendor) {
        String message = "Dear " + CustomerName + ", your product " + Product
                + " is shipped. Thank you for using our store " + Vendor + " :)";
        return message;
    }

    public String messageTempelate4(String CustomerName, String Product) {
        String message = "Dear " + CustomerName + ", your product " + Product
                + " is delivered. Thank you for using our store :)";
        return message;
    }

}
