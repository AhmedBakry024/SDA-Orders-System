package sda.orderssystem.service.NotificationService;


import sda.orderssystem.model.Order;

// this is the abstract class that will be used to create the notification channels
// it is implemented as a factory
public abstract class ChannelFactory {

    // this method will be used to create the notification in the concrete factories
    public abstract boolean createNotification(Order order);

    // these four methods will be used as a template for the messages
    // they will be used in the concrete factories
    // Each factory will use the template that is suitable for the channel
    // each template has a different message, different number of placeholders and different situations 
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

    // this method will be used to get the instance of the hashmap
    


}
