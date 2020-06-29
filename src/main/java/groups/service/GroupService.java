package groups.service;

import groups.dto.CreateGroupDto;
import groups.dto.GroupDto;
import groups.model.Group;

import java.util.List;

public interface GroupService {
    List<Group> findAll();
    Group findById(int id);
    int save(CreateGroupDto createGroupDto);
    void update(GroupDto groupDto);
    void delete(int id);
}
