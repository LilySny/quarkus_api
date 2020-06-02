package customer;

import contact.Contact;
import document.Document;
import order.Order;
import person.NaturalPerson;

import java.time.LocalDate;
import java.util.*;

public class NaturalCustomer implements NaturalPerson, Customer {

    public NaturalCustomer() {
    }

    public String getName() {
        // TODO implement here
        return "";
    }

    public Document getDocument() {
        // TODO implement here
        return null;
    }

    public String getLastname() {
        // TODO implement here
        return "";
    }

    public LocalDate getBirthdate() {
        // TODO implement here
        return null;
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