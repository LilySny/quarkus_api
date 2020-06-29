package auth.service;

import auth.dto.LoginDto;
import auth.dto.LoginResponse;
import auth.jwt.TokenService;
import exceptions.InvalidLoginException;
import security.service.CryptographyService;
import user.model.User;
import user.service.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@ApplicationScoped
public class AuthenticationFacade {

    public AuthenticationFacade(UserService userService, TokenService jwt, CryptographyService cryptographyService){
        this.userService = userService;
        this.jwt = jwt;
        this.cryptographyService = cryptographyService;
    }

    @Inject
    private UserService userService;

    @Inject
    private TokenService jwt;

    @Inject
    private CryptographyService cryptographyService;


    @Transactional
    public LoginResponse login(@NotNull LoginDto loginDto) {
        try{
        User user = this.validateLogin(loginDto.username, loginDto.password);
            String token = jwt.generateAccessToken(user);
            System.out.println("token: " + token + " id: " + user.getId());
            return new LoginResponse(token, "Usuario logado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException( e.getMessage());
        }
    }

    private User validateLogin(final String email, final String password) throws InvalidLoginException {
        final User user = this.userService.findUserByEmail(email);
        System.out.println(user);


         if(user != null && user.getPassword().equals(password)){
            return user;
        }else {
            throw new InvalidLoginException("Usuário inválido");
        }
    }
}


