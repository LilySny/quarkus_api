package person;

import contact.Contact;
import document.Document;

/**
 * 
 */
public interface JuridicalPerson extends Person {


    public Document getDocument();


    public String getName();


    public Contact getContact();

}