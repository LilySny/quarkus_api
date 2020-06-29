package user.dao;

import user.dto.CreateUserDto;
import user.dto.UserDto;
import user.model.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();

    User findUserByUsername(String username);

    User findUserById(int id);

    int save(CreateUserDto createUserDto);

    void update(UserDto userDto);

    void delete(int id);
}
