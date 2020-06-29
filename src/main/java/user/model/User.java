package user.model;

import groups.model.Group;

import java.util.List;


public interface User {
    void setId(int id);
    int getId();
    void setUsername(String username);
    String getUsername();
    String getPassword();
    List<Group> getGroups();
}
