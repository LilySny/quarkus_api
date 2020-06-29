package groups.dao;

import com.google.inject.Inject;
import groups.dto.CreateGroupDto;
import groups.dto.GroupDto;
import groups.model.Group;
import io.agroal.api.AgroalDataSource;

import javax.enterprise.context.ApplicationScoped;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class GroupMySQLDao implements GroupDao {
    public GroupMySQLDao(AgroalDataSource db){
        this.db = db;
    }

    @Inject
    AgroalDataSource db;

    @Override
    public List<Group> findAll() {
        try(Connection connection = db.getConnection()){
            PreparedStatement ps = connection.prepareStatement("SELECT group_id, group_name FROM groups");
            ResultSet resultSet = ps.executeQuery();

            List<Group> groups = new ArrayList<>();
            while(resultSet.next()){
                Group group = new Group();
                group.setId(resultSet.getInt(1));
                group.setName(resultSet.getString(2));
                groups.add(group);
            }

            return groups;

        }catch (SQLException e){
            e.getMessage();
            throw new RuntimeException();
        }
    }

    @Override
    public Group findById(int id)
    {
        try(Connection connection = db.getConnection()){
            PreparedStatement ps = connection.prepareStatement("SELECT group_id, group_name FROM groups WHERE group_id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            Group group = new Group();
            while (rs.next()){
                group.setId(rs.getInt("group_id"));
                group.setName(rs.getString("group_name"));
            }

            return group;
        } catch (SQLException e){
            e.getMessage();
            throw new RuntimeException();
        }
    }

    @Override
    public int save(CreateGroupDto createGroupDto) {
        int id = 0;
        try(Connection connection = db.getConnection()){
            PreparedStatement ps = connection.prepareStatement("INSERT INTO groups(group_name) VALUES (?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, createGroupDto.name);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            while (rs.next()){
                id = rs.getInt(1);
            }

            return id;
        } catch (SQLException e){
            e.getMessage();
            throw new RuntimeException();
        }
    }

    @Override
    public void update(GroupDto groupDto) {
        try(Connection connection = db.getConnection()){
            PreparedStatement ps = connection.prepareStatement("UPDATE groups SET " +
                    "group_name = ? " +
                    "WHERE group_id = ?");
            ps.setString(1, groupDto.name);
            ps.setInt(2, groupDto.id);
            ps.executeUpdate();
        } catch(SQLException e){
            e.getMessage();
            throw new RuntimeException();
        }
    }

    @Override
    public void delete(int id) {
        try(Connection connection = db.getConnection()){
            PreparedStatement ps = connection.prepareStatement("DELETE FROM groups WHERE group_id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch(SQLException e){
            e.getMessage();
        }
    }
}
