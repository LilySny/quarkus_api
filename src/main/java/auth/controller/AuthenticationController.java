package auth.controller;

import auth.dto.LoginDto;
import auth.dto.LoginResponse;
import auth.service.AuthenticationFacade;
import com.google.inject.Inject;

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
public class AuthenticationController {

    public AuthenticationController(AuthenticationFacade loginService){
        this.loginService = loginService;
    }

    @Inject
    private AuthenticationFacade loginService;

    @POST
    @PermitAll
    //@RolesAllowed({"CUSTOMER", "ADMIN"})
    public Response login(LoginDto userDto) {
        LoginResponse response = loginService.login(userDto);
        return Response.accepted()
                .entity(response)
                .build();
    }



}
