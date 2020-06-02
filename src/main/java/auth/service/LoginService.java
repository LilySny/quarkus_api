package auth.service;

import auth.dto.LoginDto;
import auth.dto.LoginResponse;
import auth.jwt.JwtUtils;
import exceptions.InvalidLoginException;
import user.model.User;
import user.service.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@ApplicationScoped
public class LoginService {

    public LoginService(UserService userService, JwtUtils jwt){
        this.userService = userService;
        this.jwt = jwt;
    }

    @Inject
    UserService userService;

    @Inject
    JwtUtils jwt;


    @Transactional
    public LoginResponse login(@NotNull LoginDto loginDto) throws InvalidLoginException {
        final String[] roles = {"USER"};
        User user = this.validateLogin(loginDto.username, loginDto.password);
        String token = jwt.generateAccessToken();
        return new LoginResponse(user, token, "Usuario logado com sucesso");
    }

    User validateLogin(String email, String password) throws InvalidLoginException {
        final User user = this.userService.findUserByEmail(email);
        if(user.getPassword().equals(password)){
            return user;
        }else {
            throw new InvalidLoginException("Usuário inválido");
        }
    }
}
