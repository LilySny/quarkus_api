package auth.controller;

import auth.dto.LoginDto;
import auth.dto.LoginResponse;
import auth.service.LoginService;
import com.google.inject.Inject;
import exceptions.InvalidLoginException;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/v1/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginController {

    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }

    @Inject
    LoginService loginService;

    @POST
    @PermitAll
    public Response login(LoginDto userDto) throws InvalidLoginException {
        LoginResponse response = loginService.login(userDto);
        return Response.accepted()
                .entity(response)
                .build();

    }
}
