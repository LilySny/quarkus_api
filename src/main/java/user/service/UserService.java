package user.service;

import user.dto.CreateUserDto;
import user.dto.UserDto;
import user.model.User;

import java.util.List;


public interface UserService {
    List<User> findAll();

    User findUserByEmail(String email);

    User findUserById(int id);

    int save(CreateUserDto createUserDto);

    void update(UserDto userDto);

    void delete(int id);
}
