package customer;

import contact.Contact;
import document.Document;
import order.Order;
import person.JuridicalPerson;

import java.util.*;


public class JuridicalCustomer implements JuridicalPerson, Customer {

   
    public JuridicalCustomer() {
    }

 
    public Document getDocument() {
        // TODO implement here
        return null;
    }

    public String getName() {
        // TODO implement here
        return "";
    }

    public Contact getContact() {
        // TODO implement here
        return null;
    }

    public List<Order> getOrders() {
        // TODO implement here
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }
}