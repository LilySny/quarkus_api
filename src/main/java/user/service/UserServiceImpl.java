package user.service;

import com.google.inject.Inject;
import user.dao.UserDao;
import user.dto.CreateUserDto;
import user.dto.UserDto;
import user.model.User;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    public UserServiceImpl(UserDao userDao){
        this.userDao = userDao;
    }

    @Inject
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        System.out.println(userDao != null);

        try {
            return userDao.findAll();
        } catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public User findUserByEmail(String email) {

        return userDao.findUserByUsername(email);
    }

    @Override
    public User findUserById(String id) {
        return userDao.findUserById(id);
    }

    @Transactional
    @Override
    public String save(CreateUserDto createUserDto) {
        return userDao.save(createUserDto);
    }

    @Override
    public void update(UserDto userDto) {
        userDao.update(userDto);
    }

    @Override
    public void delete(String id) {
        userDao.delete(id);
    }
}
