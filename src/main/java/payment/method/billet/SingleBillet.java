package payment.method.billet;

import java.util.*;


public class SingleBillet implements BankingBillet {


    public SingleBillet() {
    }


    public String getBarcode() {
        // TODO implement here
        return "";
    }


    public Date getDueDate() {
        // TODO implement here
        return null;
    }


    public Date getProcessDate() {
        // TODO implement here
        return null;
    }

    @Override
    public void pay() {

    }
}