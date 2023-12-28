package sda.orderssystem.model;

public class SimpleOrder extends Order {


    public SimpleOrder(Order order) {
        this.address = order.getAddress();
        this.products = order.getProductByID(order);
    }

    @Override
    public Order getChild(int i) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getChild'");
    }

    @Override
    public void addOrder(Order order) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addOrder'");
    }

    @Override
    public void removeOrder(Order order) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeOrder'");
    }
    

}
