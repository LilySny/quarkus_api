package payment.method.card;

import java.util.*;

/**
 * 
 */
public class CreditCard implements Card {

    /**
     * Default constructor
     */
    public CreditCard() {
    }


    public Date getValidity() {
        // TODO implement here
        return null;
    }


    public String getNumber() {
        // TODO implement here
        return "";
    }


    public String getName() {
        // TODO implement here
        return "";
    }


    public int getParcels() {
        // TODO implement here
        return 0;
    }


    public String getCVV() {
        // TODO implement here
        return "";
    }

    @Override
    public void pay() {

    }
}