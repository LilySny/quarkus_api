package user.dao;

import io.agroal.api.AgroalDataSource;
import user.dto.CreateUserDto;
import user.dto.UserDto;
import user.model.User;
import user.model.UserImpl;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UserSQLDao implements UserDao{

  public UserSQLDao(AgroalDataSource agroalDataSource){
      this.db = agroalDataSource;
  }

    @Inject
   private AgroalDataSource db;

    @Override
    public List<User> findAll() {
        try {
            final PreparedStatement preparedStatement =
                    db.getConnection()
                            .prepareStatement("SELECT id, username, password FROM users");
           ResultSet rs = preparedStatement.executeQuery(); // define o resultSet como a query executada
            List<User> users = new ArrayList<User>();
            while (rs.next()){
                UserImpl user = new UserImpl();
                user.setId( rs.getString(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));

               users.add(user);
            }
            return users;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e);
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public User findUserByUsername(String username){

        try {
            final PreparedStatement preparedStatement =
                    db.getConnection()
                    .prepareStatement("SELECT id, username, password FROM users WHERE username = ?");
            preparedStatement.setString(1,username);

         ResultSet resultSet =  preparedStatement.executeQuery(); // define o resultSet como a query executada
         UserImpl user = new UserImpl();

          while(resultSet.next()){ // itera até encontrar o username passado no param
              user.setId(resultSet.getString("id"));
              user.setUsername( resultSet.getString("username")); // setta o username como o username do banco
              user.setPassword(resultSet.getString("password"));
          }
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public User findUserById(String id) {
        try {
            final PreparedStatement ps =
                    db.getConnection()
                            .prepareStatement("SELECT id, username from users WHERE id = ?");
            ps.setString(1, id);
            ResultSet resultSet = ps.executeQuery();
            UserImpl user = new UserImpl();
            while (resultSet.next()) {
                user.setId(resultSet.getString("id"));
                user.setUsername(resultSet.getString("username"));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String save(CreateUserDto createUserDto){
         String id = null;
        try{
            PreparedStatement ps = db.getConnection()
                    .prepareStatement("INSERT INTO users (username, password) VALUES (?,?)",
                            Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, createUserDto.username);
            ps.setString(2, createUserDto.password);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()){
                    id = rs.getString(1);
            }

            return id;
        } catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public void update(UserDto userDto) {
        try{
            PreparedStatement ps = db.getConnection()
                    .prepareStatement("UPDATE users SET (username) WHERE id = ?");
            ps.setString(1, userDto.id.toString());
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(String id) {
        try {
            PreparedStatement ps = db.getConnection()
                    .prepareStatement("DELETE FROM users WHERE id = ?");
            ps.setString(1, id);
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

}
