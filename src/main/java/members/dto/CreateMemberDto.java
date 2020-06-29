package members.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class CreateMemberDto {
    public int groupId;
    public int userId;

    public CreateMemberDto(){};

    public CreateMemberDto(int groupId, int userId) {
        this.groupId = groupId;
        this.userId = userId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
