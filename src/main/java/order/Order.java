package order;

import java.util.*;

/**
 * 
 */
public interface Order {


    public List<OrderItem> getItems();

    public double getItemTotal();

    public List<OrderStatus> getStatus();

}