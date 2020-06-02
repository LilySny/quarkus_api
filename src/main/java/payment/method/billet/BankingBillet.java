package payment.method.billet;

import payment.method.PaymentMethod;

import java.util.*;

/**
 * 
 */
public interface BankingBillet extends PaymentMethod {

    public String getBarcode();

    public Date getDueDate();

    public Date getProcessDate();

}