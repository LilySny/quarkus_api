package payment.method.card;

import java.util.*;

/**
 * 
 */
public class StoreCard implements Card {

    public StoreCard() {
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