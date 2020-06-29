package groups.service;

import com.google.inject.Inject;
import groups.dto.GroupDto;
import groups.model.Group;
import groups.dao.GroupDao;
import groups.dto.CreateGroupDto;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class GroupServiceImpl implements GroupService {
    public GroupServiceImpl(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Inject
    GroupDao groupDao;

    @Override
    public List<Group> findAll() {
        try{
            return groupDao.findAll();
        } catch (RuntimeException e){
            e.getMessage();
            throw new RuntimeException();
        }
    }

    @Override
    public Group findById(int id) {
        try{
            return groupDao.findById(id);
        } catch (RuntimeException e){
            e.getMessage();
            throw new RuntimeException();
        }
    }

    @Override
    public int save(CreateGroupDto createGroupDto) {
        try{
            return groupDao.save(createGroupDto);
        } catch (RuntimeException e){
            e.getMessage();
            throw new RuntimeException();
        }
    }

    @Override
    public void update(GroupDto groupDto) {
        try{
            groupDao.update(groupDto);
        } catch (RuntimeException e){
            e.getMessage();
            throw new RuntimeException();
        }
    }

    @Override
    public void delete(int id) {
        try{
            groupDao.delete(id);
        } catch (RuntimeException e){
            e.getMessage();
            throw new RuntimeException();
        }
    }
}
