package groups.dao;

import groups.dto.GroupDto;
import groups.model.Group;
import groups.dto.CreateGroupDto;

import java.util.List;

public interface GroupDao {

    List<Group> findAll();
    Group findById(int id);
    int save(CreateGroupDto createGroupDto);
    void update(GroupDto groupDto);
    void delete(int id);

}
