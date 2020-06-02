package order;

import product.Product;

public interface OrderItem {

    public int getAmount();

    public Product getProduct();

    public double getPrice();

}