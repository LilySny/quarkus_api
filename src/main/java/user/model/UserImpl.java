package user.model;

import groups.model.Group;
import io.quarkus.runtime.annotations.RegisterForReflection;

import java.util.List;
import java.util.Objects;

@RegisterForReflection
public class UserImpl implements User{
    private int id;
    private String username;
    private String password;
    private List<Group> groups;

    public UserImpl(){}

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, groups);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    @Override
    public List<Group> getGroups() {
        return this.groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserImpl user = (UserImpl) o;
        return id == user.id &&
                username.equals(user.username) &&
                password.equals(user.password) &&
                Objects.equals(groups, user.groups);
    }
}
