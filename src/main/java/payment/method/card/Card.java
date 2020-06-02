package payment.method.card;

import payment.method.PaymentMethod;

import java.util.*;

/**
 * 
 */
public interface Card extends PaymentMethod {


    public Date getValidity();


    public String getNumber();


    public String getName();


    public int getParcels();


    public String getCVV();

}