package person;

import contact.Contact;
import document.Document;
import user.model.User;

/**
 * 
 */
public interface Person extends User {




    public String getName();


    public Document getDocument();


    public Contact getContact();

}