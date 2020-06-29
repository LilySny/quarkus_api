package members.model;

import groovy.transform.ToString;
import io.quarkus.runtime.annotations.RegisterForReflection;

@ToString
@RegisterForReflection
public class Member {
    private int id;
    private int userId;
    private int groupId;

    public Member() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
