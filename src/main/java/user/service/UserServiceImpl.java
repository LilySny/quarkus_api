package user.service;

import com.google.inject.Inject;
import user.dao.UserDao;
import user.dto.CreateUserDto;
import user.dto.UserDto;
import user.model.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    public UserServiceImpl(UserDao userDao){ this.userDao = userDao; }

     @Inject
     private UserDao userDao;

    @Override
    public List<User> findAll() {
        System.out.println(userDao != null);

        try {
            return userDao.findAll();
        } catch (RuntimeException e){
            throw new RuntimeException("userServiceImpl problem");
        }
    }

    @Override
    public User findUserByEmail(String email) {

        try {
            return userDao.findUserByUsername(email);
        } catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public User findUserById(int id) {

        try {
            return userDao.findUserById(id);
        } catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public int save(CreateUserDto createUserDto) {

        try {
            return userDao.save(createUserDto);
        } catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public void update(UserDto userDto) {

        try {
            userDao.update(userDto);
        } catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try {
            userDao.delete(id);
        } catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }

    }
}
