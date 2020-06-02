package customer;

import java.util.*;
import order.Order;
import person.Person;

public interface Customer extends Person {

    public List<Order> getOrders();

}