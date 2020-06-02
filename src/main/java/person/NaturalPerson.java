package person;

import contact.Contact;
import document.Document;

import java.time.LocalDate;

/**
 * 
 */
public interface NaturalPerson extends Person {


    public String getName();


    public Document getDocument();


    public String getLastname();


    public LocalDate getBirthdate();


    public Contact getContact();

}