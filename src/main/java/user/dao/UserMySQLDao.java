package user.dao;

import groups.model.Group;
import io.agroal.api.AgroalDataSource;
import user.dto.CreateUserDto;
import user.dto.UserDto;
import user.model.User;
import user.model.UserImpl;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UserMySQLDao implements UserDao{

  public UserMySQLDao(AgroalDataSource agroalDataSource){
      this.db = agroalDataSource;
  }

    @Inject
   private AgroalDataSource db;

    @Override
    public List<User> findAll() {
        try (Connection connection = db.getConnection()) {
            final PreparedStatement preparedStatement =
                    connection
                            .prepareStatement(
                                    "SELECT user_id, user_username, user_password FROM users" );

            ResultSet rs = preparedStatement.executeQuery(); // define o resultSet como a query executada
            List<User> users = new ArrayList<User>();
            while (rs.next()){
                UserImpl user = new UserImpl();
                user.setId( rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));

               users.add(user);
            }
            return users;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public User findUserByUsername(String username){
        try(Connection connection = db.getConnection()) {
            final PreparedStatement preparedStatement =
                    connection
                    .prepareStatement("SELECT user_id, user_username, user_password, group_name, group_id, member_id FROM users" +
                            " INNER JOIN members ON members.users_user_id = user_id" +
                            " INNER JOIN `groups` ON members.groups_group_id = `groups`.group_id" +
                            " WHERE user_username = ?");
            preparedStatement.setString(1,username);

            ResultSet resultSet = preparedStatement.executeQuery();
            UserImpl user = null;
            List<Group> groups = new ArrayList<>();
          while(resultSet.next()){ // itera até encontrar o username passado no param
             if(user == null || user.getId() != resultSet.getInt("user_id")) {
                  user = new UserImpl();
                  user.setId(resultSet.getInt("user_id"));
                  user.setUsername(resultSet.getString("user_username")); // setta o username como o username do banco
                  user.setPassword(resultSet.getString("user_password"));
             }

              Group group = new Group();
              group.setId(resultSet.getInt("group_id"));
              group.setName(resultSet.getString("group_name"));
              groups.add(group);


              user.setGroups(groups);

              user.setId(resultSet.getInt("user_id"));
              user.setUsername(resultSet.getString("user_username"));
              user.setPassword(resultSet.getString("user_password"));
          }
            return user;

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public User findUserById(int id) {
        try(Connection connection = db.getConnection()) {
            final PreparedStatement ps =
                    connection
                            .prepareStatement("SELECT user_id, user_username, group_name, group_id, member_id FROM users" +
                                    " INNER JOIN members ON members.users_user_id = users.user_id" +
                                    " INNER JOIN `groups` ON members.groups_group_id = `groups`.group_id" +
                                    " WHERE users.user_id = ?");
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            UserImpl user = null;
            List<Group> groups = new ArrayList<>();
            while (resultSet.next()) {
                if(user == null || user.getId() != resultSet.getInt("user_id")) {
                    user = new UserImpl();
                    user.setId(resultSet.getInt("user_id"));
                    user.setUsername(resultSet.getString("user_username")); // setta o username como o username do banco
                }

                Group group = new Group();
                group.setId(resultSet.getInt("group_id"));
                group.setName(resultSet.getString("group_name"));
                groups.add(group);

               user.setGroups(groups);

                 user.setId(resultSet.getInt("user_id"));
                 user.setUsername(resultSet.getString("user_username"));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public int save(CreateUserDto createUserDto){
         int id = 0;
        try (Connection connection = db.getConnection()){
            PreparedStatement ps = connection
                    .prepareStatement("INSERT INTO users (user_username, user_password) VALUES (?,?)",
                            Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, createUserDto.username);
            ps.setString(2, createUserDto.password);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()){
                    id = rs.getInt(1);
            }

            return id;
        } catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public void update(UserDto userDto) {
        try (Connection connection = db.getConnection()){
            PreparedStatement ps = connection
                    .prepareStatement("UPDATE users SET user_username = ? WHERE user_id = ?");
            ps.setString(1, userDto.username);
            ps.setInt(2, userDto.id);
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try(Connection connection = db.getConnection()) {
            PreparedStatement ps = connection
                    .prepareStatement("DELETE u, m FROM users u" +
                            "INNER JOIN members m ON m.users_user_id = u.user_id" +
                            "WHERE u.user_id = ?;");
            ps.setInt(1, id);
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

}


/*
"SELECT user_id, user_username, group_name, group_id, member_id FROM users" +
                            " INNER JOIN members ON members.users_user_id = users.user_id" +
                            " INNER JOIN `groups` ON members.groups_group_id = `groups`.group_id" +
                            " WHERE users.user_username = ?"
 */
